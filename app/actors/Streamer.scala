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
  case class Push(jsValue: JsValue)
}

class Streamer extends Actor with ActorLogging {
  import Streamer._
  val (in, out) = Concurrent.broadcast[JsValue]
  override def receive = {
    case Push(jsValue) => out push jsValue
    case ClientRequestStream =>
      sender ! in
      log.info("Got a ClientRequestStream request")
    case DriverStream =>
      sender ! in
      log.info("Got a DriverRequestStream request")
    case strange => log.info("strange message {} of type {}", strange, strange getClass)
  }
}
