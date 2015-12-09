package com.indix.petstore.api

import com.indix.petstore.models.User
import org.scalatest.{FlatSpec, Matchers}
import spray.json._
import com.indix.petstore.api.JsonFormats._

class JsonFormatsSpec extends FlatSpec with Matchers {

  "JsonFormats#User" should "format user to json" in {
    val user = """{
        | "name" : "foo",
        | "first_name" : "foo",
        | "last_name" : "bar",
        | "email" : "foo@bar.com",
        | "password" : ""
        |}""".stripMargin.parseJson.convertTo[User]

    user should be(User(None, "foo", Some("foo"), Some("bar"), Some("foo@bar.com"), Some(""), None))
  }
}
