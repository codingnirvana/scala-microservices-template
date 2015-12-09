package com.indix.petstore.services

import com.indix.petstore.dao.UserRepo
import com.indix.petstore.models.User
import com.indix.petstore.services.UserService.UserServiceResult
import com.typesafe.config.Config

import scala.concurrent.{ Future, ExecutionContext }

class UserService(userRepo: UserRepo, config: Config,
                  implicit val executionContext: ExecutionContext) {

  def dropSchema = userRepo.dropSchema
  def createSchema = userRepo.createSchema

  def createUser(user: User) = userRepo.create(user)

  def removeUser(name: String): Future[UserServiceResult] = userRepo.delete(name).map {
    case false => UserService.NotFound
    case true  => UserService.Success
  }

  def get(name: String) = userRepo.get(name)
}

object UserService {

  sealed trait UserServiceResult

  case object Success extends UserServiceResult
  case object NotFound extends UserServiceResult

}
