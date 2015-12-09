package com.indix.petstore

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.indix.petstore.api.Routes
import com.indix.petstore.dao.PetStorePostgresDriver.api._
import com.indix.petstore.dao.UserPgRepo
import com.indix.petstore.services.UserService
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

object PetStore extends App {

  val config = ConfigFactory.load()
  val database = Database.forConfig(s"${config.getString("petstore.env")}.petstoredb")
  val executionContext = ExecutionContext.global
  val repo = new UserPgRepo(database, executionContext)
  val log = LoggerFactory.getLogger(this.getClass)

  implicit val ec = ExecutionContext.global
  val userService = new UserService(repo, config, ec)

  if (config.getBoolean(s"${config.getString("petstore.env")}.schema.recreate")) {
    log.info("Dropping and creating schema")
    for {
      _ <- userService.dropSchema
      _ <- userService.createSchema
    } yield ()
  }

  implicit val system = ActorSystem("akka-petstore-service")
  implicit val materializer = ActorMaterializer()

  new AkkaHttp(new Routes(userService, materializer), config, system, materializer).run()

}