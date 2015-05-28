# Example dead code 

Also used for initial tests. 

### Test the plugin

- run `sbt package` in project's root folder
- get the dist jar in `target/scala-2.1.1/scala-linter_2.11-0.0.1.jar`
- copy the jar to this `example` folder
````
scalac -Xplugin:scala-linter_2.11-0.0.1.jar unused.scala
scala -cp . Unused 
````

### Use scala's built in dead code detection
````
scala -Ywarn-dead-code unused.scala
scala -Ywarn-unused unused.scala
````

btw: scala's built in dead code detection is already very advanced ....