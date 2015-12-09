package com.indix.petstore.api

import com.indix.petstore.models.User
import spray.json.DefaultJsonProtocol._

object JsonFormats {

  implicit val userFormat = jsonFormat(User, "id", "name", "first_name", "last_name", "email", "password", "phone")

}
