import sbt.Keys.*
import sbt.*
import scoverage.ScoverageKeys.coverageEnabled

val libName = "uri-template"

ThisBuild / scalaVersion := "2.13.12"

lazy val uriTemplating = (project in file("."))
  .settings(
    name := libName,
    majorVersion := 1,
    libraryDependencies ++= Seq(
      "org.scalatest"       %% "scalatest"    % "3.2.19" % Test,
      "org.pegdown"          % "pegdown"      % "1.6.0"  % Test,
      "com.vladsch.flexmark" % "flexmark-all" % "0.64.8" % Test,
      "org.scala-lang.modules" % s"scala-parser-combinators_2.13" % "1.1.2"
    ),
    scalacOptions := Seq( "-Xlint", "-target:jvm-1.8", "-encoding", "UTF-8"),
    Test / coverageEnabled := true
  )
coverageExcludedPackages := "<empty>;uk.gov.hmrc.BuildInfo;uritemplate.*"

