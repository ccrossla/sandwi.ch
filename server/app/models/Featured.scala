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
 
  //not done
  def getBestSandwichIngredientsWithCreators (db: Database)(implicit ec: ExecutionContext): Future[Seq[(String, Seq[Int], String)]] = {
    val s = getBestSandwichesU(db, 4)
  s.flatMap{f => 
       val nameIngreds =(for (x<-f) yield {
        val ingreds = getIngredients(x._1, db)
        ingreds.map(i => (x._2, i, x._3))
      })
      Future.sequence(nameIngreds)
      }
    
  }
    
  
  /*
  def getIngredientsforBestSandwiches(db: Database)(implicit ec: ExecutionContext): Future[Seq[(String, Seq[Int])]] = {
    val s = getBestSandwiches(db, 4)
    s.flatMap{f => 
       val nameIngreds =(for (x<-f) yield {
        val ingreds = getIngredients(x._1, db)
        ingreds.map(i => (x._2, i))
      })
      Future.sequence(nameIngreds)
      }
    
  }*/
  def parseI(i: Int): String = {
    if(i==1) return "White Bread"
    else if(i==2) return "Wheat Bread"
    else if(i==3) return "Tomato"
    else if(i==4) return "Lettuce"
    else if(i==5) return "Cheddar"
    else if(i==6) return "Swiss"
    else if(i==7) return "Salt"
    else if(i==8) return "Pepper"
    else if(i==9) return "Oil"
    else if(i==10) return "Mayonnaise"
    else if(i==11) return "Mustard"
    else if(i==12) return "Chicken"
    else if(i==13) return "Ham"
    else return "Turkey"
  }

  /*
  
  def attemptingJoins(db: Database)(implicit ec: ExecutionContext) = {
    val sandwicheswithIngredients = for {  def getBestSandwichCreators (db: Database)(implicit ec: ExecutionContext): Future[Seq[(Int, String, String)]] = {
    val s = getBestSandwiches(db, 4)
    
    
  }
      a <-Sandwiches
      r <-Sandwichingredient
      if(a.id===r.sid)
    } yield (a, r)
    val sandwichesIngredsLikes = for {
      a<-sandwicheswithIngredients 
      r<-Likes
      if(a._1.id===r.sid)
    } yield (a, r)
    
  }
  
  */
  /*
  def getBestSandwiches(db: Database, num: Int)(implicit ec: ExecutionContext): Future[Seq[(Int, String)]] = {
    val sandFuture = getSandwiches(db)
    sandFuture.flatMap { f =>
          val sandwichAndLikes = (for (x <- f) yield {
            val qf = getLikes(x._1, db).map(f=> f.length)
            qf.map(q => (x, q))
          })
          Future.sequence(sandwichAndLikes).map(_.sortBy(t => -t._2).take(num).map(_._1)) 
    }
  }*/
  def getBestSandwichesU(db: Database, num: Int)(implicit ec: ExecutionContext): Future[Seq[(Int, String, String)]] = {
    val sandFuture = getSandwichesWithMaker(db)
    sandFuture.flatMap { f =>
          val sandwichAndLikes = (for (x <- f) yield {
            val qf = getLikes(x._1, db).map(f=> f.length)
            qf.map(q => (x, q))
          })
          Future.sequence(sandwichAndLikes).map(_.sortBy(t => -t._2).take(num).map(_._1)) 
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
  /*
  def getSandwiches(db:Database)(implicit ec: ExecutionContext): Future[Seq[(Int, String)]] = {
    db.run {
        (for (s <- Sandwiches) yield (s.id, s.name)).result
     }
    
  }*/
  
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