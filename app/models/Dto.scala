package models

/**
 * Created by pnagarjuna on 22/04/15.
 */

case class Client(source: String, destination: String, id: Option[Long] = None)

case class Driver(model: String, timing: String, rating: Int, desc: String, id: Option[Long] = None)