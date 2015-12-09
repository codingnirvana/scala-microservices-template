package com.indix.petstore.dao

import com.github.tminglei.slickpg.PgDate2Support
import slick.driver.PostgresDriver

object PetStorePostgresDriver extends PostgresDriver with PgDate2Support {

  override val api = new API with DateTimeImplicits

  val plainAPI = new API with Date2DateTimePlainImplicits
}