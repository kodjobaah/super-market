name := """hello-scala"""

version := "1.0"

scalaVersion := "2.11.7"

val compileDependencies = Seq(
  "com.github.scopt" %% "scopt" % "3.3.0"
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

libraryDependencies ++= compileDependencies ++ testDependencies

mainClass in assembly := Some("com.main.SuperMarket")

assemblyJarName in assembly := "supermarket.jar"

fork in run := true