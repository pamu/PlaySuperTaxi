package cache

import akka.actor.{ActorLogging, Actor}
import models.{Driver, ClientRequest}

/**
 * Created by pnagarjuna on 22/04/15.
 */
object Cache {
  case class AddClientRequest(clientRequest: ClientRequest)
  case class AddDriver(driver: Driver)
  case class Engage(clientId: Long, driverId: Long)
  case object Clients
  case object Drivers
  case object Engages

  case object Stats
}

class Cache extends Actor with ActorLogging {

  var clients = Map.empty[Long, ClientRequest]
  var drivers = Map.empty[Long, Driver]
  var engaged = Map.empty[Long, Long]

  import Cache._

  override def receive = {
    case AddClientRequest(clientRequest) if !(clients contains clientRequest.idno) =>
      clients += (clientRequest.idno -> clientRequest)
    case AddDriver(driver) if !(drivers contains driver.idno) => drivers += (driver.idno -> driver)
    case Engage(clientId, driverId) if (clients contains clientId) && (drivers contains driverId) =>
      engaged += (clientId -> driverId)
      engaged += (driverId -> clientId)
    case Stats =>
      if (clients.isEmpty)  log.info("No Clients")
      else log.info("Clients {}", clients mkString("\n", "\n", "\n"))

      if (drivers.isEmpty) log.info("No Drivers")
      else log.info("Drivers {}", drivers mkString("\n", "\n", "\n"))

      if (engaged.isEmpty) log.info("No Engagements")
      else log.info("Engagements {}", engaged mkString("\n", "\n", "\n"))

    case strange => log.info("strange message {} of type {}", strange, strange.getClass)
  }
}
