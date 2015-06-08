name := "scala-linter"

organization := "org.dy.lint"

version := "0.0.5"

scalaVersion := "2.11.6"

description := "Dead code detection linter for scala"

libraryDependencies <+= scalaVersion { (scalaVersion) =>
  "org.scala-lang" % "scala-compiler"  % scalaVersion
}