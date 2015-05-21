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

## Similar projects

These projects also include similar projects in their readme

https://github.com/HairyFotr/linter
https://github.com/scalastyle/scalastyle
https://github.com/scala/scala-abide