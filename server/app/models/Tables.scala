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
  lazy val schema: profile.SchemaDescription = Ingredients.schema ++ Likes.schema ++ Sandwiches.schema ++ Sandwichingredient.schema ++ User.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Ingredients
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(15,true) */
  case class IngredientsRow(id: Int, name: String)
  /** GetResult implicit for fetching IngredientsRow objects using plain SQL queries */
  implicit def GetResultIngredientsRow(implicit e0: GR[Int], e1: GR[String]): GR[IngredientsRow] = GR{
    prs => import prs._
    IngredientsRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table ingredients. Objects of this class serve as prototypes for rows in queries. */
  class Ingredients(_tableTag: Tag) extends profile.api.Table[IngredientsRow](_tableTag, Some("sandwich"), "ingredients") {
    def * = (id, name) <> (IngredientsRow.tupled, IngredientsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> IngredientsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(15,true) */
    val name: Rep[String] = column[String]("name", O.Length(15,varying=true))
  }
  /** Collection-like TableQuery object for table Ingredients */
  lazy val Ingredients = new TableQuery(tag => new Ingredients(tag))

  /** Entity class storing rows of table Likes
   *  @param sid Database column sid SqlType(INT)
   *  @param uid Database column uid SqlType(INT) */
  case class LikesRow(sid: Int, uid: Int)
  /** GetResult implicit for fetching LikesRow objects using plain SQL queries */
  implicit def GetResultLikesRow(implicit e0: GR[Int]): GR[LikesRow] = GR{
    prs => import prs._
    LikesRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table likes. Objects of this class serve as prototypes for rows in queries. */
  class Likes(_tableTag: Tag) extends profile.api.Table[LikesRow](_tableTag, Some("sandwich"), "likes") {
    def * = (sid, uid) <> (LikesRow.tupled, LikesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(sid), Rep.Some(uid))).shaped.<>({r=>import r._; _1.map(_=> LikesRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column sid SqlType(INT) */
    val sid: Rep[Int] = column[Int]("sid")
    /** Database column uid SqlType(INT) */
    val uid: Rep[Int] = column[Int]("uid")

    /** Foreign key referencing Sandwiches (database name likes_ibfk_1) */
    lazy val sandwichesFk = foreignKey("likes_ibfk_1", sid, Sandwiches)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name likes_ibfk_2) */
    lazy val userFk = foreignKey("likes_ibfk_2", uid, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (sid,uid) (database name sid) */
    val index1 = index("sid", (sid, uid), unique=true)
  }
  /** Collection-like TableQuery object for table Likes */
  lazy val Likes = new TableQuery(tag => new Likes(tag))

  /** Entity class storing rows of table Sandwiches
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param uid Database column uid SqlType(INT)
   *  @param name Database column name SqlType(VARCHAR), Length(15,true) */
  case class SandwichesRow(id: Int, uid: Int, name: String)
  /** GetResult implicit for fetching SandwichesRow objects using plain SQL queries */
  implicit def GetResultSandwichesRow(implicit e0: GR[Int], e1: GR[String]): GR[SandwichesRow] = GR{
    prs => import prs._
    SandwichesRow.tupled((<<[Int], <<[Int], <<[String]))
  }
  /** Table description of table sandwiches. Objects of this class serve as prototypes for rows in queries. */
  class Sandwiches(_tableTag: Tag) extends profile.api.Table[SandwichesRow](_tableTag, Some("sandwich"), "sandwiches") {
    def * = (id, uid, name) <> (SandwichesRow.tupled, SandwichesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(uid), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> SandwichesRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uid SqlType(INT) */
    val uid: Rep[Int] = column[Int]("uid")
    /** Database column name SqlType(VARCHAR), Length(15,true) */
    val name: Rep[String] = column[String]("name", O.Length(15,varying=true))

    /** Foreign key referencing User (database name sandwiches_ibfk_1) */
    lazy val userFk = foreignKey("sandwiches_ibfk_1", uid, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Sandwiches */
  lazy val Sandwiches = new TableQuery(tag => new Sandwiches(tag))

  /** Entity class storing rows of table Sandwichingredient
   *  @param sid Database column sid SqlType(INT)
   *  @param iid Database column Iid SqlType(INT) */
  case class SandwichingredientRow(sid: Int, iid: Int)
  /** GetResult implicit for fetching SandwichingredientRow objects using plain SQL queries */
  implicit def GetResultSandwichingredientRow(implicit e0: GR[Int]): GR[SandwichingredientRow] = GR{
    prs => import prs._
    SandwichingredientRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table SandwichIngredient. Objects of this class serve as prototypes for rows in queries. */
  class Sandwichingredient(_tableTag: Tag) extends profile.api.Table[SandwichingredientRow](_tableTag, Some("sandwich"), "SandwichIngredient") {
    def * = (sid, iid) <> (SandwichingredientRow.tupled, SandwichingredientRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(sid), Rep.Some(iid))).shaped.<>({r=>import r._; _1.map(_=> SandwichingredientRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column sid SqlType(INT) */
    val sid: Rep[Int] = column[Int]("sid")
    /** Database column Iid SqlType(INT) */
    val iid: Rep[Int] = column[Int]("Iid")

    /** Foreign key referencing Ingredients (database name SandwichIngredient_ibfk_2) */
    lazy val ingredientsFk = foreignKey("SandwichIngredient_ibfk_2", iid, Ingredients)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Sandwiches (database name SandwichIngredient_ibfk_1) */
    lazy val sandwichesFk = foreignKey("SandwichIngredient_ibfk_1", sid, Sandwiches)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Sandwichingredient */
  lazy val Sandwichingredient = new TableQuery(tag => new Sandwichingredient(tag))

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
