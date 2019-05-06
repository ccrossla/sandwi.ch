package util

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.MySQLProfile", "com.mysql.cj.jdbc.Driver", 
    "jdbc:mysql://localhost/sandwich?user=sandwich&password=MonteRye&nullNamePatternMatchesAll=true&serverTimezone=UTC", 
    "/home/mlewis/workspaceCourses/CSCI3345-S19/server/app/", 
    //TODO who is gonna put their info here ^^^^
    "models", Option("mlewis"), Option("password"), true, false
  )
}