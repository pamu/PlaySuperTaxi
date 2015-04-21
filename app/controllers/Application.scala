package controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

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

}
