package util

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.MySQLProfile", "com.mysql.cj.jdbc.Driver", 
    "jdbc:mysql://localhost/sandwich?user=sandwich&password=MonteRye&nullNamePatternMatchesAll=true&serverTimezone=UTC", 
   "/users/gvirgen/webApps/sandwi.ch/server/app/", 
    "models", Option("sandwich"), Option("MonteRye"), true, false
  )
}