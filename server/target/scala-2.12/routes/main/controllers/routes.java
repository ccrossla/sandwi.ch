// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/carolinecrossland/Desktop/workspace/sandwi.ch/server/conf/routes
// @DATE:Thu Mar 28 17:19:11 CDT 2019

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
