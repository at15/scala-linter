# scala-linter

Dead code detection for scala

Detect the following three types of deadcode

1. code in unreachable and never executed
2. dead code that has no external impact
3. dead code that has negative external impact, ie: division by zero

## TODO

- [ ] write sample deadcode
- [ ] init the sbt project for plugin
- [ ] T1 statement after return
- [ ] T1 if branch never executed, never match etc.
- [ ] T2 unused var
- [ ] T2 var has no contribution to final result
- [ ] T3 T2 which throw exception or cause IO
- [ ] apply it to a small project
- [ ] autofixer

## Ref sites

http://lampwww.epfl.ch/~magarcia/ScalaCompilerCornerReloaded/
https://github.com/ymasory/alacs/blob/master/dev/resources.md

## Similar projects

These projects also include similar projects in their readme

https://github.com/HairyFotr/linter
https://github.com/scalastyle/scalastyle
https://github.com/scala/scala-abide

scala-abide core rules Unused local definitions, Variables that are never assigned
linter Unused xx ?

## Projects that may help

- https://github.com/jrudolph/scalac-plugin.g8  // a template for scalac plugin using sbt
- https://github.com/harrah/browse
- https://github.com/jrudolph/scala-enhanced-strings
- https://github.com/jrudolph/sbt-dependency-graph
