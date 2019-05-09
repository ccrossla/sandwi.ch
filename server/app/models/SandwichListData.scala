package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext

object SandwichListData {
  def getSandwiches(db: Database)(implicit ec: ExecutionContext):Future[Option[Int]] = {
    
  }
}