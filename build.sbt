import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning
import scoverage.ScoverageKeys.coverageEnabled

val libName = "uri-template"

lazy val uriTemplating = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    name := libName,
    majorVersion := 1,
    makePublicallyAvailableOnBintray := true,
    resolvers := Seq(Resolver.bintrayRepo("hmrc", "releases")),
    libraryDependencies ++= scalaBinaryVersion {
      case v if Set("2.13", "2.12") contains v =>
        Seq("org.scala-lang.modules" % s"scala-parser-combinators_$v" % "1.1.2")
      case "2.11" =>
        Seq("org.scala-lang.modules" % s"scala-parser-combinators_2.11" % "1.0.7")
      case _ =>
        Nil
    }.value,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.8" % "test",
      "org.pegdown" % "pegdown" % "1.6.0" % "test"
    ),
    scalacOptions := Seq( "-Xlint", "-target:jvm-1.8", "-encoding", "UTF-8"),
    crossScalaVersions := List(scala213, scala212, scala211),
    scalaVersion := scala211,
    coverageEnabled in Test := true
  )

lazy val scala213 = "2.13.1"
lazy val scala212 = "2.12.10"
lazy val scala211 = "2.11.12"
lazy val parserCombinatorsVersion = "1.1.2"

coverageExcludedPackages := "<empty>;uk.gov.hmrc.BuildInfo;uritemplate.*"

