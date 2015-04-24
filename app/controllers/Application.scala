package controllers

import actors.Streamer.{DriverStream, ClientRequestStream}
import akka.util.Timeout
import cache.Cache.{AddClientRequest, AddDriver}
import global.Global
import models.{ClientRequest, Driver}
import play.api.libs.EventSource
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.{Json, JsError, JsSuccess, JsValue}
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.duration._

import akka.pattern.ask

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Welcome to SuperTaxi Dispatcher Service."))
  }

  def clientRequest() = Action.async(parse.json) { implicit request =>
    Future {
      import models.JsonUtils._
      request.body.validate[ClientRequest] match {
        case success: JsSuccess[ClientRequest] =>
          val data = success.get
          Global.cache ! AddClientRequest(data)
          Ok(Json.obj("status" -> 200))
        case e: JsError =>
          Status(BAD_REQUEST).as("application/json")
      }
    }
  }

  def driver() = Action.async(parse.json) { implicit request =>
    Future {
      import models.JsonUtils._
      request.body.validate[Driver] match {
        case success: JsSuccess[Driver] =>
          val data = success.get
          Global.cache ! AddDriver(data)
          Ok(Json.obj("status" -> 200))
        case e: JsError =>
          Status(BAD_REQUEST).as("application/json")
      }
    }
  }

  def dashboard() = Action { implicit request =>
    Ok(views.html.dashboard())
  }

  def dashboardClientStream() = Action.async {
    implicit val timeout = Timeout(5 seconds)
    val future = (Global.streamer ? ClientRequestStream).mapTo[Enumerator[String]]
    future.map(stream => Ok.chunked(stream &> EventSource()).as(EVENT_STREAM))
  }

  def dashboardDriverStream()  = Action.async {
    implicit val timeout = Timeout(5 seconds)
    val future = (Global.streamer ? DriverStream).mapTo[Enumerator[String]]
    future.map(stream => Ok.chunked(stream &> EventSource()).as(EVENT_STREAM))
  }

  def drivers(id: Long) = Action {
    val driver = Driver(id, 1, "very experienced", Some(1))
    Global.cache ! AddDriver(driver)
    Ok("done")
  }

  def clients(id: Long) = Action {
    val client = ClientRequest(id, "hyderabad", "delhi", Some(1))
    Global.cache ! AddClientRequest(client)
    Ok("done")
  }

}
