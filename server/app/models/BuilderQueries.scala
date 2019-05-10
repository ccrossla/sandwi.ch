package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext

object BuilderQueries {
  def addSandwich(db: Database, uid: Int, uName: String)(implicit ec: ExecutionContext): Future[Int] = {
    println(uName)
    db.run {
      Sandwiches += SandwichesRow(0, uid, (uName + " sandwich"))
    }
  }
  def getSid(db: Database, uid: Int)(implicit ec: ExecutionContext): Future[Seq[(Int, String)]] = {
    db.run {
      (for (s <- Sandwiches if s.uid === uid) yield (s.id, s.name)).result
    }
  }
  def updateName(db: Database, sid: Int, sname: String)(implicit ec: ExecutionContext): Future[Int] = {
    db.run {
      val s = for { s <- Sandwiches if s.id === sid } yield s.name 
      s.update(sname)
    }
  }

  def addIngredient(db: Database, sid: Int, iid: Int)(implicit ec: ExecutionContext): Future[Int] = {  
    db.run {
      Sandwichingredient += SandwichingredientRow(sid, iid)

    }  
  }
  def getAllIngredients(db: Database)(implicit ec: ExecutionContext): Future[Seq[Int]] = {
    db.run {
      (for (i <- Ingredients) yield i.id).result
    }
  }
  
  def getSandwich(db: Database, sid: Int)(implicit ec: ExecutionContext): Future[Seq[Int]] = {
    db.run {
      (for{
        i <- Sandwichingredient
        if i.sid === sid
      } yield{
        i.iid
      }).result
        
    }
  }
  
  
  
  //  QUERIES TO ADD
  //  def resetSandwich
  //This will delete the sandwich in the table, remove all ingredients associated with it in
  //sandwichIngredient, and refresh the page, calling addSandwich again to create a new
  //sandwich

}
