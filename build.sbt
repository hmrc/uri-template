import sbt.Keys.*
import sbt.*
import scoverage.ScoverageKeys.coverageEnabled

val libName = "uri-template"

lazy val uriTemplating = (project in file("."))
  .settings(
    name := libName,
    majorVersion := 1,
    libraryDependencies ++= scalaBinaryVersion {
      case v if Set("2.13", "2.12") contains v =>
        Seq("org.scala-lang.modules" % s"scala-parser-combinators_$v" % "2.4.0")
      case "2.11" =>
        Seq("org.scala-lang.modules" % s"scala-parser-combinators_2.11" % "2.2.0")
      case _ =>
        Nil
    }.value,
    libraryDependencies ++= Seq(
      "org.scalatest"       %% "scalatest"    % "3.2.19" % Test,
      "org.pegdown"          % "pegdown"      % "1.6.0"  % Test,
      "com.vladsch.flexmark" % "flexmark-all" % "0.64.8" % Test
    ),
    scalacOptions := Seq( "-Xlint", "-target:jvm-11", "-encoding", "UTF-8"),
    crossScalaVersions := List(scala213, scala212, scala211),
    scalaVersion := scala213,
    Test / coverageEnabled := true
  )

lazy val scala213 = "2.13.12"
lazy val scala212 = "2.12.10"
lazy val scala211 = "2.11.12"

coverageExcludedPackages := "<empty>;uk.gov.hmrc.BuildInfo;uritemplate.*"

