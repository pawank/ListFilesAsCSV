import sbt._

object Version {
  val scala     = "2.11.7"
  val scalaTest = "2.2.4"
  val slf4j     = "1.7.6"
  val ammoniteReplV = "0.5.2"
  val scalacsvV = "1.2.2"
}

object Library {
  val scalaTest      = "org.scalatest"     %% "scalatest"       % Version.scalaTest
  val slf4jApi       = "org.slf4j"         %  "slf4j-api"       % Version.slf4j
  val ammoniteRepl   = "com.lihaoyi" % "ammonite-repl" % Version.ammoniteReplV cross CrossVersion.full
  val ammoniteOps    = "com.lihaoyi" %% "ammonite-ops" % Version.ammoniteReplV
  val scalaCSV       = "com.github.tototoshi" %% "scala-csv" % Version.scalacsvV
}

object Dependencies {

  import Library._

  val deps = Seq(
    ammoniteRepl,
    ammoniteOps,
    scalaCSV,
    scalaTest      % "test"
  )
}
