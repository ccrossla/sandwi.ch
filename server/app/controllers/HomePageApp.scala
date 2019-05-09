package controllers

import edu.trinity.webapps.shared.SharedMessages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import javax.inject._
import scala.concurrent.Future
import models.Tables._
import slick.jdbc.MySQLProfile.api._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

import slick.jdbc.MySQLProfile.api._ // This line determines what type of database you are connecting to.

// form case classes
case class sand(name: String, ingreds: Seq[Int])
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

@Singleton
class HomePageApp @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {
    
  //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  def index = Action.async { implicit request =>
   
    models.Featured.getBestSandwichIngredientsWithCreators(db: Database, 4).map(f=> Ok(views.html.index(f)))
 
  }


 
}
