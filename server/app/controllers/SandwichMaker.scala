

package controllers

import javax.inject._
//import edu.trinity.webapps.shared.SharedMessages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import javax.inject._
import scala.concurrent.Future

import models._
import Tables._

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

import slick.jdbc.MySQLProfile.api._ // This line determines what type of database you are connecting to.

@Singleton
class SandwichMaker @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  def build = Action.async { implicit request =>
    val u = request.session("uid").toInt
    models.BuilderQueries.addSandwich(db, u) //future

    models.BuilderQueries.getAllIngredients(db).flatMap(f => { //then future this is bad
      models.BuilderQueries.getSid(db, u).map(x =>
        Ok(views.html.builder(u, f, x.last._1, x.last._2)))
    })

  }
  def changeName = Action.async { implicit request =>
  
    val postBody = request.body.asFormUrlEncoded
    postBody.map { args =>
      val sid = args("sid").head.toInt
      val sname = args("sname").head
      BuilderQueries.updateName(db, sid, sname).flatMap(f => {
        models.BuilderQueries.getAllIngredients(db).map(q => Ok(views.html.builder(request.session("uid").toInt, q, sid, sname)))
      })
    }.getOrElse(Future(Redirect("rebuild", 200)))

  }

  def rebuild = Action.async { implicit request =>
    val u = request.session("uid").toInt
    models.BuilderQueries.getAllIngredients(db).flatMap(f => {
      models.BuilderQueries.getSid(db, u).map(x =>
        Ok(views.html.builder(u, f, x.last._1, x.last._2)))
    })
  }
  
  
  
  
  def addIngredient = Action.async { implicit request =>
    val postBody = request.body.asFormUrlEncoded
    postBody.map { args =>
      val sid = args("sid").head.toInt
      val iid = args("iid").head.toInt
      val sname = args("sname").head
      val success = BuilderQueries.addIngredient(db, sid, iid)
      success.flatMap { q =>
        if (q > 0) {
          val allgreeds = models.BuilderQueries.getAllIngredients(db)
          allgreeds.map { ingred =>
            if (ingred != None) {
              Ok(views.html.builder(request.session("uid").toInt, ingred, sid, sname))
            } else {
              Redirect(routes.SandwichMaker.rebuild)
            }
          }

        } else {
          Future(Redirect(routes.SandwichMaker.rebuild))
        }
      }

    }.getOrElse(Future.successful(Redirect(routes.SandwichMaker.rebuild)))

  }
} 
  
 