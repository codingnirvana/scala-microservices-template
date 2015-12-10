package com.indix.petstore.services

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.indix.petstore.dao.UserRepo
import com.indix.petstore.models.User
import com.typesafe.config.Config
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ FlatSpec, Matchers }

import scala.concurrent.{ Future, ExecutionContext }

class UserServiceSpec extends FlatSpec with Matchers with MockFactory with ScalaFutures {

  implicit val executionContext = ExecutionContext.global
  implicit val actorSystem = ActorSystem()
  implicit val materializer = ActorMaterializer()
  val userRepo = mock[UserRepo]
  val config = mock[Config]

  val userService = new UserService(userRepo, config, executionContext)

  "UserService#createUser" should "create user" in {
    (userRepo.create _)
      .expects(userWithoutId)
      .returning(Future(userWithId))

    val result = userService.createUser(userWithoutId).futureValue

    result should be(userWithId)
  }

  val userWithoutId = User(None, "foo", Some("foo"), Some("bar"), Some("foo@bar.com"), Some(""), None)
  val userWithId = userWithoutId.copy(id = Some(12323))
}
