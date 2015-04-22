package models

import play.api.libs.json.{Writes, JsPath, Reads}
import play.api.libs.functional.syntax._

/**
 * Created by pnagarjuna on 22/04/15.
 */

case class ClientRequest(idno: Long, source: String, destination: String, id: Option[Long] = None)

case class Driver(idno: Long, rating: Int, desc: String, id: Option[Long] = None)

case class Service(clientId: Long, driverId: Long, timing: String, id: Option[Long] = None)

object JsonUtils {
  val clientRequestReads: Reads[ClientRequest] = (
    (JsPath \ "idno").read[Long] and
      (JsPath \ "source").read[String] and
      (JsPath \ "destination").read[String] and
      (JsPath \ "id").read[Option[Long]]
    ) (ClientRequest.apply _)

  val driverReads: Reads[Driver]  = (
    (JsPath \ "idno").read[Long] and
      (JsPath \ "rating").read[Int] and
      (JsPath \ "desc").read[String] and
      (JsPath \ "id").read[Option[Long]]
    ) (Driver.apply _)

  val clientRequestWrites: Writes[ClientRequest] = (
    (JsPath \ "idno").write[Long] and
      (JsPath \ "source").write[String] and
      (JsPath \ "destination").write[String] and
      (JsPath \ "id").write[Option[Long]]
    ) (unlift(ClientRequest.unapply))

  val driverWrites: Writes[Driver] = (
    (JsPath \ "idno").write[Long] and
      (JsPath \ "rating").write[Int] and
      (JsPath \ "desc").write[String] and
      (JsPath \ "id").write[Option[Long]]
    ) (unlift(Driver.unapply))
}
