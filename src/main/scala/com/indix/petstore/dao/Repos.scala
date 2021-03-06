package com.indix.petstore.dao

import com.indix.petstore.models._

import scala.concurrent.Future

trait Repo {
  def createSchema: Future[Unit]
  def dropSchema: Future[Unit]
}

trait UserRepo extends Repo {

  /**
   * POST: Create a [[User]].
   * @param user The User we want to add to the database.
   * @return The added User.
   */
  def create(user: User): Future[User]

  /**
   * GET: Get [[User]] by username, assume all usernames are unique.
   * @param name The username of the User we want to find.
   * @return The User in question.
   */
  def get(name: String): Future[Option[User]]

  /**
   * DELETE: Delete a [[User]] by their username.
   * @param name The username of the User to be deleted.
   */
  def delete(name: String): Future[Boolean]

  /**
   * PUT: Update [[User]]. Note that usernames cannot be changed because they are the unique identifiers by which the
   * system finds existing users.
   * @param user The updated version of the old User.
   * @return The updated User.
   */
  def update(user: User): Future[User]
}

trait OrderRepo {

  /**
   * POST: Place an [[Order]] for a [[Pet]].
   * @param order The order object to be placed with the petstore.
   * @return The autogenerated ID of the order object.
   */
  def create(order: Order): Future[Long]

  /**
   * GET: Find purchase [[Order]] by ID
   * @param id The ID of the order to find.
   * @return The Order object in question.
   */
  def get(id: Long): Future[Order]

  /**
   * DELETE: Delete purchase [[Order]] by ID
   * @param id The ID of the order to delete.
   * @return true if deletion was successful. false otherwise.
   */
  def delete(id: Long): Future[Boolean]
}

trait PetRepo {

  /**
   * GET: Finds a [[Pet]] by its ID.
   *
   * @param id The ID of the [[Pet]] we're looking for
   * @return The [[Pet]] object
   */
  def get(id: Long): Future[Pet]

  /**
   * POST: Adds a [[Pet]] to the database, validating that the ID is empty.
   *
   * @param pet the new pet
   * @return the id of the new pet
   */
  def create(pet: Pet): Future[Long]

  /**
   * PUT: Updates an existing [[Pet]], while validating that a current version of
   * the [[Pet]] exists (a.k.a. an existing [[Pet]] has the same id as inputPet).
   * @param inputPet The [[Pet]] we want to replace the current [[Pet]] with. Must be passed with the original Pet's ID.
   * @return The updated pet
   */
  def update(inputPet: Pet): Future[Pet]

  /**
   * DELETE: Deletes a [[Pet]] from the database.
   * @param id The ID of the Pet to be deleted.
   * @return true if deletion was successful. false otherwise.
   */
  def deletePet(id: Long): Future[Unit]

  /**
   * POST: Update a [[Pet]] in the store with form data
   * @param petId ID of the Pet to be updated.
   * @param n New name of the Pet.
   * @param s New status of the Pet.
   * @return The updated Pet.
   */
  def updatePetNameStatus(petId: Long, n: Option[String], s: Option[Status]): Future[Pet]

  /**
   * Helper method: Allows the user to get all the pets in the database.
   * @return A sequence of all pets in the store.
   */
  def allPets: Future[Seq[Pet]]

  /**
   * GET: Returns the current [[Inventory]].
   * @return A map of how many pets currently correspond to which Status type.
   */
  def getInventory: Future[Inventory]

  /**
   * Helper method to add category
   * Adds a [[Category]] to the category list
   * @param category The category we want to add
   * @return The category just added
   */
  def addCat(category: Category): Future[Category]

  /**
   * Helper method for addPet: Adds a [[Tag]] to the tag map
   * This differs from the Swagger example in that the Tag's ID is autogenerated
   * rather than passed by the user. This is done to avoid potential memory and storage
   * sabotage. Tags with given IDs will be rejected.
   * @param inputTag The tag we want to add
   * @return The tag just added
   */
  def addTag(inputTag: Tag): Future[Tag]

}