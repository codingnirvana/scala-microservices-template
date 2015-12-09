package com.indix.petstore.models

/**
 * Represents Pets in the Petstore. Each Pet has a unique ID that should not be known by
 * the user at the time of its creation.
 * @param id The pet's auto-generated, unique ID.
 * @param name (Required) The pet's name.
 * @param photoUrls (Required) A sequence of URLs that lead to uploaded photos of the pet.
 * @param category The type of pet (cat, dragon, fish, etc.)
 * @param tags Tags that describe this pet.
 * @param status (Available, Pending, or Adopted)
 */
case class Pet(
  id: Option[Long],
  name: String,
  photoUrls: Seq[String],
  category: Option[Category],
  tags: Option[Seq[Tag]],
  status: Option[Status] //available, pending, adopted
  )

/**
 * Represents the general status of a [[Pet]]. This should either be [[Available]], [[Pending]], or [[Adopted]].
 */
sealed trait Status {
  /**
   * @return The string representing the value of this status.
   */
  def code: String
}

/**
 * The status of a [[Pet]] when it is available for adoption.
 */
case object Available extends Status {
  /**
   * @return The string representing the value of this status: "available"
   */
  def code: String = "available"
}

/**
 * The status of a [[Pet]] when it is pending for adoption, and currently unavailable for purchase.
 */
case object Pending extends Status {
  /**
   * @return The string representing the value of this status: "pending"
   */
  def code: String = "pending"
}

/**
 * The status of a [[Pet]] when it has been adopted.
 */
case object Adopted extends Status {
  /**
   * @return The string representing the value of this status: "adopted"
   */
  def code: String = "adopted"
}

