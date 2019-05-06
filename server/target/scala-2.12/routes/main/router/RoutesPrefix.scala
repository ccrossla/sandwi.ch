// @GENERATOR:play-routes-compiler
// @SOURCE:/users/gvirgen/webApps/sandwi.ch/server/conf/routes
// @DATE:Mon May 06 14:28:53 CDT 2019


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
