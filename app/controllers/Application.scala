package controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello :)"))
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

  def dashboardClientStream() = Action {
    Ok()
  }

  def dashboardDriverStream()  = Action {
    Ok()
  }

}
