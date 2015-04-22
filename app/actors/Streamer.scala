package actors

import akka.actor.{ActorLogging, Actor}
import play.api.libs.iteratee.Concurrent
import play.api.libs.json.JsValue

/**
 * Created by pnagarjuna on 22/04/15.
 */
object Streamer {
  case object ClientRequestStream
  case object DriverStream
  case class PushClientRequest(jsValue: JsValue)
  case class PushDriver(jsValue: JsValue)
}

class Streamer extends Actor with ActorLogging {
  import Streamer._
  val (cin, cout) = Concurrent.broadcast[String]
  val (din, dout) = Concurrent.broadcast[String]

  override def receive = {
    case PushClientRequest(jsValue) =>
      log.info("got a Push client request message {}", jsValue)
      cout push jsValue.toString()
    case PushDriver(jsValue) =>
      log.info("got a Pus driver message {}", jsValue)
      dout push jsValue.toString()
    case ClientRequestStream =>
      sender ! cin
      log.info("Got a ClientRequestStream request")
    case DriverStream =>
      sender ! din
      log.info("Got a DriverRequestStream request")
    case strange => log.info("strange message {} of type {}", strange, strange getClass)
  }
}
