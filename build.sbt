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
    libraryDependencies <++= scalaBinaryVersion {
      case v if Set("2.12", "2.11") contains v =>
        Seq("org.scala-lang.modules" % s"scala-parser-combinators_$v" % parserCombinatorsVersion)
      case _ =>
        Nil
    },
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5" % "test",
      "org.pegdown" % "pegdown" % "1.6.0" % "test"
    ),
    crossScalaVersions := List(scala212, scala211),
    scalaVersion := scala212,
    coverageEnabled in Test := true
  )

lazy val scala212 = "2.12.8"
lazy val scala211 = "2.11.12"
lazy val parserCombinatorsVersion = "1.0.7"

coverageExcludedPackages := "<empty>;uk.gov.hmrc.BuildInfo;uritemplate.*"

