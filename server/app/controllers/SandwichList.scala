package controllers

import javax.inject._

import play.api.mvc._
import models._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.JdbcProfile

@Singleton
<<<<<<< HEAD
class SandwichList @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  // def allSandwiches = Action { implicit request =>
   // Ok(views.html.userProfile("testing"))
  //}
=======
class SandwichList @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {
   def allSandwiches =  Action.async { implicit request =>
    val fsands = SandwichListData.getSandwiches(db)
    fsands.map { sand =>
      Ok(views.html.userProfile(sand))
    }
   }
>>>>>>> caba776e0880995bccda9348e3958a2f04b69603
}