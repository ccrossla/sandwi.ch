package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext

object SandwichListData {
  def getSandwiches(db: Database)(implicit ec: ExecutionContext): Future[Seq[Sandwich]] = {
    val sandwichFuture = db.run {
      (for {
        s <- Sandwiches
      } yield s).result
    }
    sandwichFuture.flatMap { sRow =>
      val ingredientFuture = db.run {
        (for {
          sing <- Sandwichingredient
          if sing.sid === sRow.head.id
          ing <- Ingredients
          if ing.id === sing.iid
        } yield ing.name).result
      }
      val likesFuture = db.run {
        Likes.filter(_.sid === sRow.head.id).size.result
      }
      val userFuture = db.run {
        User.filter(_.id === sRow.head.uid).result
      }
      Future.sequence(sRow.map { sand =>
        for {
          ings <- ingredientFuture
          likes <- likesFuture
          users <- userFuture
        } yield Sandwich(sand.name, users.head.username, ings, likes)
      })
    }
  }

  def likeSandwich(db: Database, sid: Int, uid: Int)(implicit ec: ExecutionContext): Future[Boolean] = {
    val existsf = likeExists(db, sid, uid)
    existsf.flatMap { exists =>
      if (exists == None) {
        (db.run {
          Likes += LikesRow(sid, uid)
        }).map(_ > 0)
      } else {
        return Future.successful(false)
      }
    }
  }

  def likeExists(db: Database, sid: Int, uid: Int)(implicit ec: ExecutionContext): Future[Option[Int]] = {
    val ids = db.run {
      (for {
        l <- Likes
        if l.sid === sid
        if l.uid === uid
      } yield {
        l.uid
      }).result
    }
    ids.map(i => i.headOption)
  }
}