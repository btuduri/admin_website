name := "admin"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.webjars" % "bootstrap" % "3.2.0",
  "org.webjars" % "webjars-play" % "2.1.0-1",
  "mysql" % "mysql-connector-java" % "5.1.32"
)


play.Project.playJavaSettings
