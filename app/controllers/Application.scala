package controllers

import play.api.libs.EventSource
import play.api.libs.iteratee.Enumerator
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Welcome to SuperTaxi Dispatcher Service."))
  }

  def clientRequest() = Action.async(parse.json) { implicit request =>
    Future {
      Ok("saved")
    }
  }

  def driver() = Action.async {
    Future {
      Ok("saved")
    }
  }

  def dashboard() = Action { implicit request =>
    Ok(views.html.dashboard())
  }

  val stream = Enumerator("java", "scala")

  def dashboardClientStream() = Action {
    Ok.chunked(stream &> EventSource()).as(EVENT_STREAM)
  }

  def dashboardDriverStream()  = Action {
    Ok.chunked(stream &> EventSource()).as(EVENT_STREAM)
  }

}
