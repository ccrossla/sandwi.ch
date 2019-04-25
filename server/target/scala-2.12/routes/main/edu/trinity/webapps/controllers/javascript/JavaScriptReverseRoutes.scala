// @GENERATOR:play-routes-compiler
// @SOURCE:/users/gvirgen/webApps/sandwi.ch/server/conf/routes
// @DATE:Thu Apr 25 13:48:36 CDT 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:6
package edu.trinity.webapps.controllers.javascript {

  // @LINE:6
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "edu.trinity.webapps.controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}
