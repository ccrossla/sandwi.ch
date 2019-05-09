import org.scalatest._
import org.scalatestplus.play._
import play.api.http.MimeTypes
import play.api.test._
import org.scalatestplus.play.guice.GuiceOneServerPerSuite

class LoginBrowserApplicationSpec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  "The Sandwi.ch Login App" must {
    "try to log in a user" in {
      go to s"http://localhost:$port/userWelcome"
      click on "username"
      textField("username").value = "yayo"
      click on "password"
      textField("password").value = "pass123"
      submit()
    }

  }

  "The Sandwi.ch Login App" must {
    "try to create  a user" in {
      go to s"http://localhost:$port/userWelcome"
      click on "username"
      textField("username").value = "yayo"
      click on "password"
      textField("password").value = "pass123"
      submit()
    }

  }
}