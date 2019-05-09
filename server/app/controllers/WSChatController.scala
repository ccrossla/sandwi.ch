package controllers


import play.api.mvc._

import play.api.libs.streams.ActorFlow

import akka.actor.ActorSystem

import akka.stream.Materializer

import actors._

import akka.actor.Props


import javax.inject.Inject

import play.api.http.MimeTypes

import play.api.routing._



@Singleton
class WSChatController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {

  val manager = system.actorOf(Props[ChatManager], "manager")

  

  def index = Action { implicit request =>

    Ok(views.html.chatPage())

  }

  

  def socket = WebSocket.accept[String, String] { request =>

    ActorFlow.actorRef { out =>

      ChatWebSocketActor.props(out, manager)

    }

  }

  

  

}