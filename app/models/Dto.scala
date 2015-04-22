package models

/**
 * Created by pnagarjuna on 22/04/15.
 */

case class ClientRequest(idno: Long, source: String, destination: String, id: Option[Long] = None)

case class Driver(idno: Long, model: String, rating: Int, desc: String, id: Option[Long] = None)

case class Service(clientId: Long, driverId: Long, timing: String, id: Option[Long] = None)
