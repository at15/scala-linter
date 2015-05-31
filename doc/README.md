# Documentation

## Usage

- Make sure you have sbt installed
- run `sbt package` in project's root folder
- get the dist jar in `target/scala-2.1.1/scala-linter_2.11-0.0.1.jar`

### Run `scalac` with the plugin directly
````
scalac -Xplugin:scala-linter_2.11-0.0.1.jar unused.scala
scala -cp . Unused
````

### Use in sbt
- copy the jar to project folder, ie: project's `lib` folder'.
- change scalcOptions in build.sbt
````
scalacOptions ++= Seq[String]("-Xplugin:lib/scala-linter_2.11-0.0.1.jar")
````
Then plugin will be loaded

NOTE:Use `addCompilerPlugin` is not working, I don't know why though.

## Other

- [Resources](resources.md) useful projects and links
- [Review](review/README.md) review about above resources
- [Compiler](compiler/README.md) basic knowledge about compiler and scala compiler
- [Problems](problems/README.md) solved and pending problems
- [How to use IDEA for sbt projects](idea.md)
- [Scala dev environment setup](env-setup.md)
