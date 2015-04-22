package controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello :)"))
  }

  def client(id: Long, source: String, destination: String) = Action.async {
    Future {
      import models.SampleDb._
      save(id, source, destination)
      Ok("saved")
    }
  }

  def driver(id: Long, driverInfo: String) = Action.async {
    Future {
      import models.SampleDb._
      save(id, driverInfo)
      Ok("saved")
    }
  }

  def dashboard() = Action { implicit request =>
    Ok(views.html.dashboard())
  }

}
