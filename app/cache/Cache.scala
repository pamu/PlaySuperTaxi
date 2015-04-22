package cache

import akka.actor.{ActorRef, ActorLogging, Actor}
import models.{Driver, ClientRequest}
import play.api.libs.json.Json

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

class Cache(streamer: ActorRef) extends Actor with ActorLogging {

  var clients = Map.empty[Long, ClientRequest]
  var drivers = Map.empty[Long, Driver]
  var engaged = Map.empty[Long, Long]

  import Cache._
  import actors.Streamer._
  import models.JsonUtils._

  override def receive = {
    case AddClientRequest(clientRequest) =>
      log.info("got a add client request message")
      clients += (clientRequest.idno -> clientRequest)
      streamer ! PushClientRequest(Json.toJson(clientRequest))

    case AddDriver(driver) =>
      log.info("got a add driver request message")
      drivers += (driver.idno -> driver)
      streamer ! PushDriver(Json.toJson(driver))

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
