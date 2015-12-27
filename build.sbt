import com.github.retronym.SbtOneJar.oneJarSettings

oneJarSettings

name := "ListFilesAsCSV"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Dependencies.deps

initialCommands in (Test,console) :=
  """
    |import com.typesafe.config.ConfigFactory
    |ammonite.repl.Main.run("")
  """.stripMargin


exportJars := true

mainClass in oneJar := Some("apps.Program")

mainClass in (Compile, run) := Some("apps.Program")