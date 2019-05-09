package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext


object BuilderQueries {
  def validUser(user: String, pass:String, db: Database)(implicit ec: ExecutionContext): Future[Seq[String]] = {
    
  }
 def userTasks(db: Database, user: String)(implicit ec: ExecutionContext): Future[Seq[String]] = {
    db.run {
      (for {
        c <- Customer
        if c.name === user
        t <- Tasks
        if t.cusid === c.id
      } yield t.task).result
    }
  }
}