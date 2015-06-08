# Roadmap for scala-linter

This is just an experimental project for a school course. (kc-3g)

(All the other students are working in the professors lab, e.....)

The deadline is 2015/06/08, so I guess there is little time for me now.

### Ultimate goal

1. Dead code detection for scala
2. Apply linter to scalac to see if dead code is affecting scalac's speed

NOTE:Xlint is already providing a lot of dead code detection.

Detect the following three types of dead code

1. code in unreachable and never executed
2. dead code that has no external impact
3. dead code that has negative external impact, ie: division by zero

## TODO

R1 write own linter

- [x] T1 statement after return
- [x] T1 if branch never executed, never match etc.
- [x] ~~T2 unused var~~
- [x] ~~T2 var has no contribution to final result~~
- [ ] T3 T2 which throw exception or cause IO
- [x] apply it to a small  
- [ ] autofixer
- [x] apply compiler plugin to scala lang
- [x] write sample deadcode
- [x] init the sbt project for plugin

R2 Use scala's Xlint

- [x] get `-Ywarn-dead-code`, `-Ywarn-unused` result, use `scalac -Xlint:help` to see all supported result
- [x] ~~use Xlint on scala~~
- [x] log compiler warning for dead code and turn into structured data
- [x] apply it to scalac itself
- [ ] autofixer for dead code
- [ ] test compile speed

see projects https://github.com/at15/scala , https://github.com/at15/scalac-log-formatter
