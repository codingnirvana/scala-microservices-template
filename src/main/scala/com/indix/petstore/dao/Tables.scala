package com.indix.petstore.dao

import java.time.LocalDateTime

import com.indix.petstore.dao.PetStorePostgresDriver.api._

import com.indix.petstore.models.User

object UsersTable {
  val NAME = "USERS"
}

class UsersTable(tag: Tag) extends Table[User](tag, UsersTable.NAME) {

  def id = column[Long]("USER_ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def firstName = column[Option[String]]("FIRST_NAME")
  def lastName = column[Option[String]]("LAST_NAME")
  def email = column[Option[String]]("EMAIL")
  def password = column[Option[String]]("PASSWORD")
  def phone = column[Option[String]]("PHONE")

  def createdAt = column[LocalDateTime]("CREATED_AT")

  // Every table needs a * projection with the same type as the table's type parameter
  override def * = // scalastyle:ignore
    (id.?, name, firstName, lastName, email, password, phone) <> (User.tupled, User.unapply)

}