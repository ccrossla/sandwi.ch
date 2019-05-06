package controllers

import javax.inject._
import edu.trinity.webapps.shared.SharedMessages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import javax.inject._
import scala.concurrent.Future

import models._
//import Tables._

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

import slick.jdbc.MySQLProfile.api._ // This line determines what type of database you are connecting to.

// form case classes

case class UserLogin(userName: String, password: String)

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

@Singleton
class Application @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  def index = Action { implicit request =>
    Ok(views.html.index(SharedMessages.itWorks))
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  // setting up Play forms

  // login forms

  val userLog = Form(mapping(
    "username" -> nonEmptyText(1, 12),
    "password" -> nonEmptyText(1, 12))(UserLogin.apply)(UserLogin.unapply))

  // other forms???
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
     def userWelcome = Action { implicit request =>
    Ok(views.html.userHomePage(userLog))
  }
    
}
