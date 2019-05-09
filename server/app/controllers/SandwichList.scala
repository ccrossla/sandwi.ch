package controllers

import javax.inject._

import controllers.routes;
import play.api.mvc._
import models._

@Singleton
class SandwichList @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
   def allSandwiches = Action { implicit request =>
    Ok(views.html.userProfile("testing"))
  }
}