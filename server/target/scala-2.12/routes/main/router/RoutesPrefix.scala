// @GENERATOR:play-routes-compiler
// @SOURCE:/users/gvirgen/webApps/sandwi.ch/server/conf/routes
// @DATE:Thu Apr 25 13:48:36 CDT 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
