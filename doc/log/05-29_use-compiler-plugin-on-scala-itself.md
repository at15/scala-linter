# Use compiler plugin on scala itself

Since scala is trying to replace ant with sbt, we can use compiler plugin on scala language itself

Because

- sbt version use the latest stable version scala to compile. (no need for bootstrap system, see build.sbt in scala/scala)

How to do that 

NOTE: see http://www.scala-sbt.org/0.13/tutorial/Library-Dependencies.html, must specify the plguin version instead of using `%%`

- add `addCompilerPlugin("org.dy.lint" % "scala-linter_2.11" % "0.0.1")` in `project/plugins.sbt`

the modified version is in https://github.com/at15/scala. but you need to change the compiler plugin into another one because 
this linter is only published locally. (and this linter is just a error thrower currently....)

## FAQ

- how to publish local to the right position 
  
  Set `organization` in `build.sbt` other wise will publish to the `default` folder instead of `org.dy.lint`
 
## Failure
   
Some methods that won't work ( all the modification to build.sbt didn't work)

- add `scalacOptions in Compile +=  "-Xplugin:<path-to-sxr>/sxr-0.3.0.jar",`
- add `scalacOptions += "-Xplugin:<path-to-sxr>/sxr-0.3.0.jar",`