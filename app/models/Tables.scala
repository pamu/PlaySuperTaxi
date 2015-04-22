package models

/**
 * Created by pnagarjuna on 22/04/15.
 */
import scala.slick.driver.MySQLDriver.simple._

class ClientRequests(tag: Tag) extends Table[ClientRequest](tag, "clients") {
  def idno = column[Long]("idno", O.NotNull)
  def source = column[String]("source", O.NotNull)
  def destination = column[String]("destination", O.NotNull)
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def * = (idno, source, destination, id.?) <> (ClientRequest.tupled, ClientRequest.unapply)
}