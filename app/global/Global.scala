package global

import akka.actor.{Props, ActorSystem}
import cache.Cache
import play.api.{Logger, Application, GlobalSettings}

/**
 * Created by pnagarjuna on 22/04/15.
 */
object Global extends GlobalSettings {

  lazy val system = ActorSystem("SuperTaxiSystem")
  lazy val cache = system.actorOf(Props[Cache], "CacheActor")

  override def onStart(app: Application): Unit = {
    Logger.info("PlaySuperTaxi Started")
    cache ! Cache.Stats
    super.onStart(app)
  }

  override def onStop(app: Application): Unit = {
    cache ! Cache.Stats
    system.shutdown()
    Logger.info("PlaySuperTaxi Stopped")
    super.onStop(app)
  }
}
