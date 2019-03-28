
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Play with Scala.js")/*3.28*/ {_display_(Seq[Any](format.raw/*3.30*/("""
"""),format.raw/*4.1*/("""<h2>Play and Scala.js share a same message</h2>
<ul>
  <li>Play shouts out: <em>"""),_display_(/*6.29*/message),format.raw/*6.36*/("""</em></li>
  <li>Scala.js shouts out: <em id="scalajsShoutOut"></em></li>
</ul>
""")))}),format.raw/*9.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Mar 28 17:19:12 CDT 2019
                  SOURCE: /Users/carolinecrossland/Desktop/workspace/sandwi.ch/server/app/views/index.scala.html
                  HASH: 4012a1bd10dcdb5ba2f5f5bacbd0639303cddc39
                  MATRIX: 729->1|840->19|867->21|901->47|940->49|967->50|1074->131|1101->138|1211->219
                  LINES: 21->1|26->2|27->3|27->3|27->3|28->4|30->6|30->6|33->9
                  -- GENERATED --
              */
          