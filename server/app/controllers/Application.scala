package controllers

import javax.inject._
//import edu.trinity.webapps.shared.SharedMessages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import scala.concurrent.Future

import models._
import Tables._

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

import slick.jdbc.MySQLProfile.api._ // This line determines what type of database you are connecting to.

// form case classes

case class UserLogin(username: String, password: String)

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

@Singleton
class Application @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


  def index = Action { implicit request =>
    Ok(views.html.userHomePage(userLog, createUser))
    //Ok(views.html.index(SharedMessages.itWorks))

  }

  //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  // setting up Play forms

  // login forms

  val userLog = Form(mapping(
    "username" -> nonEmptyText(1, 12),
    "password" -> nonEmptyText(1, 12))(UserLogin.apply)(UserLogin.unapply))

  val createUser = Form(mapping(
    "username" -> nonEmptyText(1, 12),
    "password" -> nonEmptyText(1, 12))(UserLogin.apply)(UserLogin.unapply))

  //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  def userWelcome = Action { implicit request =>
    Ok(views.html.userHomePage(userLog, createUser))
  }

  def userLogin = Action.async { implicit request =>
    userLog.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(views.html.userHomePage(formWithErrors, createUser))),
      userData => {
        val userInfoFut = models.LoginQueries.checkUser(db,userData.username, userData.password)
          userInfoFut.map(userInfo => userInfo match {
            case Some(userInfo) => Redirect(routes.HomePageApp.index).withSession("username" -> userData.username, "uid" -> userInfo.toString)
            case None => BadRequest(views.html.userHomePage(userLog, createUser))
          })
      })
  }

  def createAUser = Action.async { implicit request =>
    createUser.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(views.html.userHomePage(userLog, formWithErrors))),
      userData => {
        val userInfoFut = models.LoginQueries.addUser(db, userData.username, userData.password)
        userInfoFut.map(userInfo => userInfo match {
          case false => BadRequest(views.html.userHomePage(userLog, createUser))
          case true => Redirect(routes.HomePageApp.index).withSession("username" -> userData.username, "uid" -> userInfo.toString)
        })
      })
  }

  def userLogout = Action { implicit request =>
    Redirect(routes.Application.userWelcome).withNewSession
  }

  //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

}
