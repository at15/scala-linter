# Documentation

## Usage

- Make sure you have sbt installed
- run `sbt package` in project's root folder
- get the dist jar in `target/scala-2.1.1/scala-linter_2.11-0.0.1.jar`
- run `scalac` with the plugin
````
scalac -Xplugin:scala-linter_2.11-0.0.1.jar unused.scala
scala -cp . Unused 
````
TODO

- use it in `sbt`
- how to run `scalac` without installing it globally.

## Other

- [Resources](resources.md) useful projects and links
- [Review](review/README.md) review about above resources 
- [Compiler](compiler/README.md) basic knowledge about compiler and scala compiler
- [Problems](problems/README.md) solved and pending problems
- [How to use IDEA for sbt projects](idea.md)
- [Scala dev environment setup](env-setup.md)
