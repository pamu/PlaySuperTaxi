package models

import java.net.URI

/**
 * Created by pamu on 20/4/15.
 */
object ProductionDb {
  import scala.slick.driver.PostgresDriver.simple._

  val uri = new URI("postgres://gsqbgrxvpyadee:KmgebP3eQ8BqgG4k2OnVPVme-E@" +
    "ec2-107-22-173-230.compute-1.amazonaws.com:5432/d778l22q1rk5mg")

  val username = uri.getUserInfo.split(":")(0)

  val password = uri.getUserInfo.split(":")(1)

  lazy val db = Database.forURL(
    driver = "org.postgresql.Driver",
    url = "jdbc:postgresql://" + uri.getHost + ":" + uri.getPort + uri.getPath, user = username,
    password = password)
}

object SampleDb {
  import scala.slick.driver.MySQLDriver.simple._

  lazy val db = Database.forURL(url = "jdbc:mysql://localhost:3306/demo", driver = "com.jdbc.mysql.Driver",
    user = "root", password = "root")

  def save(id: Long, source: String, destination: String): Unit = db.withSession { implicit sx =>
    
  }

  def save(id: Long, driverInfo: String) = db.withSession { implicit sx =>

  }
}
