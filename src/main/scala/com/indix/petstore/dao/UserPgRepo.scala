package com.indix.petstore.dao

import com.indix.petstore.dao.PetStorePostgresDriver.api._
import com.indix.petstore.models._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

import scala.concurrent.{ ExecutionContext, Future }

class UserPgRepo(db: Database,
                 implicit val executionContext: ExecutionContext)
    extends UserRepo {

  private val usersTable = TableQuery[UsersTable]

  private lazy val createUserQuery = usersTable returning usersTable.map(_.id) into
    ((user: User, id) => user.copy(id = Some(id)))

  override def createSchema: Future[Unit] = {
    db.run(
      MTable.getTables("USERS")
    ).flatMap { tables =>
        if (tables.isEmpty) {
          db.run(usersTable.schema.create)
        } else {
          db.run(DBIO.seq())
        }
      }
  }

  override def dropSchema: Future[Unit] = {
    db.run(
      MTable.getTables("USERS")
    ).flatMap { tables =>
        if (tables.nonEmpty) {
          db.run(usersTable.schema.drop)
        } else {
          db.run(DBIO.seq())
        }
      }
  }

  /**
   * PUT: Update [[User]]. Note that usernames cannot be changed because they are the unique identifiers by which the
   * system finds existing users.
   * @param user The updated version of the old User.
   * @return The updated User.
   */
  override def update(user: User): Future[User] = ???

  /**
   * GET: Get [[User]] by username, assume all usernames are unique.
   * @param name The username of the User we want to find.
   * @return The User in question.
   */
  override def get(name: String): Future[Option[User]] = {
    db.run {
      usersTable.filter(_.name === name).result
    }.map(_.headOption)
  }

  /**
   * DELETE: Delete a [[User]] by their username.
   * @param name The username of the User to be deleted.
   */
  override def delete(name: String): Future[Boolean] = {
    db.run {
      usersTable.filter(_.name === name).delete.map(_ > 0)
    }
  }

  /**
   * POST: Create a [[User]].
   * @param user The User we want to add to the database.
   * @return The user name of the added User.
   */
  override def create(user: User): Future[User] = {
    db.run {
      createUserQuery += user
    }
  }
}
