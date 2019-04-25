// @GENERATOR:play-routes-compiler
// @SOURCE:/users/gvirgen/webApps/sandwi.ch/server/conf/routes
// @DATE:Thu Apr 25 13:48:36 CDT 2019

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
