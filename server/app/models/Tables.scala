package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Sandwiches.schema ++ User.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Sandwiches
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param uid Database column uid SqlType(INT)
   *  @param ingredients Database column ingredients SqlType(VARCHAR), Length(15,true), Default(None)
   *  @param numoflikes Database column numofLikes SqlType(INT), Default(None)
   *  @param isfavorite Database column isfavorite SqlType(BIT), Default(None) */
  case class SandwichesRow(id: Int, uid: Int, ingredients: Option[String] = None, numoflikes: Option[Int] = None, isfavorite: Option[Boolean] = None)
  /** GetResult implicit for fetching SandwichesRow objects using plain SQL queries */
  implicit def GetResultSandwichesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[Boolean]]): GR[SandwichesRow] = GR{
    prs => import prs._
    SandwichesRow.tupled((<<[Int], <<[Int], <<?[String], <<?[Int], <<?[Boolean]))
  }
  /** Table description of table sandwiches. Objects of this class serve as prototypes for rows in queries. */
  class Sandwiches(_tableTag: Tag) extends profile.api.Table[SandwichesRow](_tableTag, Some("sandwich"), "sandwiches") {
    def * = (id, uid, ingredients, numoflikes, isfavorite) <> (SandwichesRow.tupled, SandwichesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(uid), ingredients, numoflikes, isfavorite)).shaped.<>({r=>import r._; _1.map(_=> SandwichesRow.tupled((_1.get, _2.get, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uid SqlType(INT) */
    val uid: Rep[Int] = column[Int]("uid")
    /** Database column ingredients SqlType(VARCHAR), Length(15,true), Default(None) */
    val ingredients: Rep[Option[String]] = column[Option[String]]("ingredients", O.Length(15,varying=true), O.Default(None))
    /** Database column numofLikes SqlType(INT), Default(None) */
    val numoflikes: Rep[Option[Int]] = column[Option[Int]]("numofLikes", O.Default(None))
    /** Database column isfavorite SqlType(BIT), Default(None) */
    val isfavorite: Rep[Option[Boolean]] = column[Option[Boolean]]("isfavorite", O.Default(None))

    /** Foreign key referencing User (database name sandwiches_ibfk_1) */
    lazy val userFk = foreignKey("sandwiches_ibfk_1", uid, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Sandwiches */
  lazy val Sandwiches = new TableQuery(tag => new Sandwiches(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(VARCHAR), Length(50,true)
   *  @param password Database column password SqlType(VARCHAR), Length(50,true) */
  case class UserRow(id: Int, username: String, password: String)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("sandwich"), "user") {
    def * = (id, username, password) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password))).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(VARCHAR), Length(50,true) */
    val username: Rep[String] = column[String]("username", O.Length(50,varying=true))
    /** Database column password SqlType(VARCHAR), Length(50,true) */
    val password: Rep[String] = column[String]("password", O.Length(50,varying=true))
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
