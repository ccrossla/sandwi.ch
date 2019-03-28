// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/carolinecrossland/Desktop/workspace/sandwi.ch/server/conf/routes
// @DATE:Thu Mar 28 17:19:11 CDT 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:6
package edu.trinity.webapps.controllers {

  // @LINE:6
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
