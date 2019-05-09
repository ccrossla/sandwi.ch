package controllers

import javax.inject._
import edu.trinity.webapps.shared.SharedMessages
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import javax.inject._
import scala.concurrent.Future
import models.Tables._
import slick.jdbc.MySQLProfile.api._



import models._
//import Tables._

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
   
    models.Featured.getBestSandwichIngredientsWithCreators(db: Database).map(f=> Ok(views.html.index(f)))
    
    
    
    /*
    val sandwicheswithIngredients = for {
      a <-Sandwiches
      r <-Sandwichingredient
      if(a.id===r.sid)
    } yield (a, r)
   
    val sandwichesIngredsLikes = for {
      a<-sandwicheswithIngredients 
      r<-Likes
      if(a._1.id===r.sid)
    } yield (a, r)
    
    db.run {
      (for(i <- sandwichesIngredsLikes;  x <- i._1._2; y <- i._2._2) yield  (i._1._1.name, x._1._2.iid, y._2.uid)).result}
     */    
    
    // if (i._1._2.sid===i._1._1.id && i._2.sid===i._1._1.id)
            
    
    
    //sand.map( f => Ok(views.html.index(f)))
    /* 
    val s = db.run {
        (for (s <- Sandwiches)
         yield  (s.id, s.name)).result}
      
       val sand = s.map(f=> { for(x <- f) yield (
         db.run { (for (l <- Likes 
                      if l.sid === x._1 
                     ) yield l.uid).result
       }, db.run { (for (i <- Sandwichingredient 
                  if i.sid === x._1 
                    ) yield i.iid).result
       }, x._2
    )
    
        })*/
       
    
   // val s = models.Featured.getIngredientsforBestSandwiches(db)
    
   // val s = List(List(models.Featured.getLikes(3, db).toString))
 
  }


 
}
