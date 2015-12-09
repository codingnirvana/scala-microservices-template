package com.indix.petstore

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.indix.petstore.api.Routes
import com.typesafe.config.Config

class AkkaHttp(routes: Routes,
               config: Config,
               implicit val actorSystem: ActorSystem,
               implicit val actorMaterializer: ActorMaterializer) {

  def run() = {
    Http().bindAndHandle(routes.routes, config.getString("interface"), config.getInt("port"))
  }

}
