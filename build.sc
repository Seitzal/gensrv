import mill._, scalalib._, publish._

trait Common extends ScalaModule {
  def scalaVersion = "2.13.2"
}

object core extends Common with PublishModule {

  def artifactName = "gensrv-core"

  def publishVersion = "0.1.0"

  def pomSettings = PomSettings(
    description = "Low boilerplate web services",
    organization = "eu.seitzal",
    url = "https://github.com/seitzal/gensrv",
    versionControl = VersionControl.github("seitzal", "gensrv"),
    licenses = Seq(License.MIT),
    developers = Seq(
      Developer("seitzal", "Alex Seitz","https://github.com/Seitzal")
    )
  )

  def ivyDeps = Agg(
    ivy"com.typesafe:config:1.4.0",
    ivy"ch.qos.logback:logback-classic:1.2.3",
    ivy"com.typesafe.scala-logging::scala-logging:3.9.2",
    ivy"org.http4s::http4s-dsl:0.21.3",
    ivy"org.http4s::http4s-blaze-server:0.21.3",
    ivy"org.tpolecat::doobie-core:0.8.8",
    ivy"org.tpolecat::doobie-postgres:0.8.8",
    ivy"org.tpolecat::doobie-hikari:0.8.8",
    ivy"com.lihaoyi::upickle:1.1.0",
    ivy"eu.seitzal::http4s-upickle:0.2.1"
  )
  def scalacOptions = Seq(
    "-deprecation"
  )
}

object example extends Common {
  def moduleDeps = Seq(core)
}