package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext

//
//object BuilderQueries {
//  def addIngredient(db: Database, sid: Int, iid: Int)(implicit ec:ExecutionContext):Future[Boolean] = {
//    db.run {
//     Sandwichingredient += SandwichingredientRow(sid, iid)
//    }
//  }
//  
//  
// 
//}