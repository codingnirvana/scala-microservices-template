package com.indix.petstore.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.indix.petstore.api.JsonFormats._
import com.indix.petstore.models.User
import com.indix.petstore.services.UserService
import org.slf4j.LoggerFactory
import spray.json._

class Routes(userService: UserService,
             implicit val materializer: ActorMaterializer) {
  private val LOG = LoggerFactory.getLogger(this.getClass)

  implicit val ec = materializer.executionContext

  val routes = {
    logRequestResult("akka-petstore-service") {
      path("users" / Segment) { name =>
        get {
          LOG.info(s"get /users/$name")
          complete {
            userService.get(name).map[ToResponseMarshallable] {
              case None       => notFound("user")
              case Some(user) => user.toJson
            }
          }
        } ~ delete {
          LOG.info(s"delete /users/$name")
          complete {
            userService.removeUser(name).map[ToResponseMarshallable] {
              case UserService.Success  => ""
              case UserService.NotFound => notFound("user")
            }
          }
        }
      }
    } ~ path("users") {
      post {
        LOG.info(s"post /users")
        entity(as[User]) { user =>
          handleWith(userService.createUser)
        }
      }
    } ~ path("status") {
      complete("Ok")
    }
  }

  private def notFound(entity: String) = NotFound -> s"The $entity could not found"
}
