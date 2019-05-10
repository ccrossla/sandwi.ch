package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext
import scala.collection.mutable.ListBuffer
import scala.util.Failure
import scala.util.Success
import scala.concurrent.duration._

case class sandLikes(sandwich: Int, tLikes: Int)

object Featured {
 
  
  def getBestSandwichIngredientsWithCreators (db: Database, num: Int)(implicit ec: ExecutionContext): Future[Seq[(String, Seq[Int], String, Int, Int)]] = {
    val s = getBestSandwichesU(db, num)
  s.flatMap{f => 
       val nameIngreds =(for (x<-f) yield {
        val ingreds = getIngredients(x._1._1, db)
        ingreds.map(i => (x._1._2, i, x._1._3,  x._2, x._1._1))
      })
      Future.sequence(nameIngreds)
      }
    
  }
    
  

  def parseI(i: Int): String = {
    if(i==1)  "White Bread"
    else if(i==2)  "Wheat Bread"
    else if(i==3)  "Tomato"
    else if(i==4)  "Lettuce"
    else if(i==5)  "Cheddar"
    else if(i==6)  "Swiss"
    else if(i==7)  "Salt"
    else if(i==8)  "Pepper"
    else if(i==9)  "Oil"
    else if(i==10) "Mayonnaise"
    else if(i==11)  "Mustard"
    else if(i==12) "Chicken"
    else if(i==13)  "Ham"
    else  "Turkey"
  }


  def getBestSandwichesU(db: Database, num: Int)(implicit ec: ExecutionContext): Future[Seq[((Int, String, String), Int)]] = {
    val sandFuture = getSandwichesWithMaker(db)
    sandFuture.flatMap { f =>
          val sandwichAndLikes = (for (x <- f) yield {
            val qf = getLikes(x._1, db).map(f=> f.length)
            qf.map(q => (x, q))
          })
          Future.sequence(sandwichAndLikes).map(_.sortBy(t => -t._2).take(num)) 
    }
  }
  
  def getIngredients(sid: Int, db:Database)(implicit ec: ExecutionContext): Future[Seq[Int]] = {
    db.run {
       (for (i <- Sandwichingredient 
        if i.sid === sid 
        ) yield i.iid).result
    }
  
    }
  

  def getSandwichesWithMaker(db:Database)(implicit ec: ExecutionContext): Future[Seq[(Int, String, String)]] = {
    val sUId = getSandwichesU(db)
    sUId.flatMap{ f => 
      val sandwichAndUser = ( for (x<-f) yield {
        val sandAndU = db.run{models.Tables.User.filter(f => f.id===x._3).result }
        sandAndU.map( d => (x._1, x._2, d.head.username))
      })
      Future.sequence(sandwichAndUser)
    }
  }

  def getSandwichesU(db:Database)(implicit ec: ExecutionContext): Future[Seq[(Int, String, Int)]] = {
    db.run {
        (for (s <- Sandwiches) yield (s.id, s.name, s.uid)).result
     }
    
  }
  
  
  def getLikes(sid: Int, db:Database)(implicit ec: ExecutionContext): Future[Seq[Int]] = {
    db.run {
       (for (l <- Likes 
        if l.sid === sid 
        ) yield l.uid).result
       }
       
  }

}