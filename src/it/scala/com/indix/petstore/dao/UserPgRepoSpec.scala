package com.indix.petstore.dao

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.indix.petstore.models.User
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import org.scalatest.{Matchers, FlatSpec, BeforeAndAfter}

import com.indix.petstore.dao.PetStorePostgresDriver.api._
import slick.jdbc.meta.MTable

import scala.concurrent.ExecutionContext

class UserPgRepoSpec extends FlatSpec with BeforeAndAfter with Matchers with ScalaFutures {

  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  implicit val actorSystem = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val executionContext = ExecutionContext.global
  val db = Database.forConfig("test.petstoredb")
  val usersRepo = new UserPgRepo(db, executionContext)

  before {
    usersRepo.dropSchema.futureValue
    usersRepo.createSchema.futureValue
  }

  "UserPgRepo#schema" should "create schema" in {
    val tables = db.run(MTable.getTables).futureValue
    tables.count(_.name.name.equalsIgnoreCase(UsersTable.NAME)) should be(1)
  }

  "UserPgRepo#schema" should "drop schema" in {
    usersRepo.dropSchema.futureValue
    val tables = db.run(MTable.getTables).futureValue
    tables.count(_.name.name.equalsIgnoreCase(UsersTable.NAME)) should be(0)
  }

  "UserPgRepo#operations" should "create a user" in {
    val createdUser = createUser("foo")
    createdUser.id.isDefined should be(true)
  }

  "UserPgRepo#operations" should "get a user by name" in {
    val createdUser = createUser("foo")
    val user = usersRepo.get("foo").futureValue
    user.isDefined should be(true)
    user.get.id.isDefined should be(true)
    createdUser.copy(id = user.get.id) should be(user.get)
  }

  private def createUser(name: String) = {
    val user = User(None, name, Some("bar"), Some("baz"), Some("foo@gmail.com"), Some("foobarbaaz"), Some("9192323"))
    usersRepo.create(user).futureValue
  }

}
