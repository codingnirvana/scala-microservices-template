package com.indix.petstore.models

/**
 * Represents a Category object for pets. This is the type of animal a pet is.
 * @param id The unique, autogenerated ID of this Category.
 * @param name The name of this Category.
 */
case class Category(id: Option[Long], name: String)