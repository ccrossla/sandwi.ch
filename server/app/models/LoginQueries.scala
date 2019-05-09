package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext

object LoginQueries {


  def checkUser(db: Database, username: String, password: String)(implicit ec: ExecutionContext): Future[Option[Int]] = {
    val uIDFut = db.run {
      (for {
        u <- User
        if u.username === username
        if u.password === password
      } yield {
        u.id
      }).result
    }
    uIDFut.map(i => i.headOption)
  }

  def existsUser(db: Database, username: String, password: String)(implicit ec: ExecutionContext): Future[Option[Int]] = {
    val uIDFut = db.run {
      (for {
        u <- User
        if u.username === username
      } yield {
        u.id
      }).result
    }
    uIDFut.map(i => i.headOption)
  }

  def addUser(db: Database, username: String, password: String)(implicit ec: ExecutionContext): Future[Boolean] = {
    val userExistsFut = existsUser(db, username, password)
    userExistsFut.flatMap { userExists =>
      if (userExists == None) {
        (db.run {
          User += UserRow(0, username, password)
        }).map(_ > 0)
      } else {
        Future.successful(false)
      }
    }
  }

} 