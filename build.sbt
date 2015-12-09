import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, _}
import spray.revolver.RevolverPlugin.Revolver

import scalariform.formatter.preferences.{AlignSingleLineCaseStatements, DoubleIndentClassDeclaration, AlignParameters}

name := "scala-microservice-template"
organization := "indix"
version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-encoding", "UTF-8", "-deprecation", "-feature", "-unchecked", "-Xfatal-warnings", "-Xlint")

mainClass in Compile := Some("com.indix.petstore.PetStore")

val akkaHttpV     = "2.0-M2"
val scalaTestV    = "2.2.5"
val scalaMockV    = "3.2.2"

libraryDependencies ++= List(
  "com.typesafe.slick"      %% "slick"                                % "3.0.1",
  "com.typesafe.akka"       %% "akka-http-core-experimental"          % akkaHttpV,
  "com.typesafe.akka"       %% "akka-http-spray-json-experimental"    % akkaHttpV,
  "com.typesafe.akka"       %% "akka-slf4j"                           % "2.3.9",
  "com.typesafe"            %  "config"                               % "1.2.1",
  "ch.qos.logback"           % "logback-classic"                      % "1.1.3",
  "org.postgresql"           % "postgresql"                           % "9.3-1103-jdbc41",
  "com.github.tminglei"     %% "slick-pg"                             % "0.9.1",
  "com.zaxxer"               % "HikariCP"                             % "2.4.1",

  "org.scalatest"           %% "scalatest"                            % scalaTestV       % "it,test",
  "org.scalamock"           %% "scalamock-scalatest-support"          % scalaMockV       % "it,test",
  "com.typesafe.akka"       %% "akka-http-testkit-experimental"       % akkaHttpV        % "it,test"
)

lazy val root = Project(
  id = "scala-microservice-template",
  base = file(".")
).configs(IntegrationTest)

Defaults.itSettings
scalariformSettings
Revolver.settings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(AlignParameters, true)

fork in run := true