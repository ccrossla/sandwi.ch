package controllers

import javax.inject._

import play.api.mvc._
import models._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.JdbcProfile
import scala.concurrent.Future

@Singleton
class SandwichList @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {
  
  def allSandwiches = Action.async { implicit request =>
    //size of database for num
   val s = models.Featured.getSandwichesU(db).map(f => f.length)
   //main call for info
   s.flatMap(f => models.Featured.getBestSandwichIngredientsWithCreators(db, f).map(x=> {
     Ok(views.html.userProfile(request.session("uid"), x))
   }))
  }
  
  def likeSandwich = Action.async {implicit request =>
    val params = request.body.asFormUrlEncoded
    params.map { data =>
      val uid = data("uid").head
      val sid = data("sid").head
      val fb = models.SandwichListData.likeSandwich(db, sid.toInt, uid.toInt)
      fb.map { b =>
        if (b) {
          Redirect(routes.SandwichList.allSandwiches).withSession("uid"->uid)
        } else {
          Redirect(routes.SandwichList.allSandwiches).withSession("uid"->uid)
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.SandwichList.allSandwiches)))
  }
  
  /*
   def allSandwiches =  Action.async { implicit request =>
    val fsands = SandwichListData.getSandwiches(db)
    fsands.map { sand =>
     // sand.map(f=> println(f.username))
      Ok(views.html.userProfile(sand))
    }
   }
	*/
}
