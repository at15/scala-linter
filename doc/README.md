# Doc for this scala-linter

## Usage

- Make sure you have sbt installed
- run `sbt package` in project's root folder
- get the dist jar in `target/scala-2.1.1/scala-linter_2.11-0.0.1.jar`

 TODO: how to run `scalac` without installing it globally.
- use it in `scalac`
````
scalac -Xplugin:scala-linter_2.11-0.0.1.jar unused.scala
scala unused
````
TODO

- use it in `sbt`


## Doc for scala beginners

[How to use idea for sbt projects](idea.md)
