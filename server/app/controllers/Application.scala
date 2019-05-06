package controllers

import javax.inject._

import edu.trinity.webapps.shared.SharedMessages
import play.api.mvc._
import play.api.i18n._
import play.api.data._
import play.api.data.Forms._

@Singleton
class Application @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

  def index = Action { implicit request =>
    Ok(views.html.index(SharedMessages.itWorks))
  }

}
