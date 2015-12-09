package com.indix.petstore.models

/**
 * Represents an order to the petstore.
 * @param id The unique, autogenerated ID of the order. The user should never give an ID during Order creation.
 * @param petId The ID of the pet being ordered.
 * @param quantity The number of pets being ordered.
 * @param shipDate The date the order will be shipped by.
 * @param status The status of the order.
 * @param complete Whether the order has been fulfilled.
 */
case class Order(
  id: Option[Long],
  petId: Option[Long],
  quantity: Option[Long],
  shipDate: Option[String],
  status: Option[OrderStatus], //placed, approved, delivered
  complete: Option[Boolean])

/**
 * Represents the status of a particular order for pets. Can be "placed," "approved," or "delivered."
 */
sealed trait OrderStatus {
  /**
   * @return The string representation of the OrderStatus.
   */
  def code: String
}

/**
 * The status of an order after it has been placed.
 */
case object Placed extends OrderStatus {
  /**
   * @return The string representation of the OrderStatus: "placed."
   */
  def code: String = "placed"
}

/**
 * The status of an order after it has been approved by the store.
 */
case object Approved extends OrderStatus {
  /**
   * @return The string representation of the OrderStatus: "approved."
   */
  def code: String = "approved"
}

/**
 * The status of an order after it has been delivered and completed.
 */
case object Delivered extends OrderStatus {
  /**
   * @return The string representation of the OrderStatus: "delivered."
   */
  def code: String = "delivered"
}

