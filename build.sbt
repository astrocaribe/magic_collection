name := """magicTheGathering"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.39",
  "org.jdbi" % "jdbi" % "2.59",
  "org.antlr" % "stringtemplate" % "3.2",
  "org.assertj" % "assertj-core" % "3.0.0" % "test",
  "org.powermock" % "powermock-module-junit4" % "1.6.1" % "test",
  "org.powermock" % "powermock-api-mockito" % "1.6.1" % "test"
)

routesGenerator := InjectedRoutesGenerator