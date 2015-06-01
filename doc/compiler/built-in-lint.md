# Scalac built in lint

`-Ywarn-dead-code` `-Ywarn-unused`

## Related files

Check unused is called in Analyzer, and **both** dead code and unused warning are generated in TypeDiagnostics

- https://github.com/scala/scala/blob/2.11.x/src/compiler/scala/tools/nsc/typechecker/Analyzer.scala
- https://github.com/scala/scala/blob/2.11.x/src/compiler/scala/tools/nsc/typechecker/TypeDiagnostics.scala

## Related issues

TODO: jira issues about lint, wrong dead code warning
https://issues.scala-lang.org/browse/SI-4919?jql=project%20%3D%20SI%20AND%20resolution%20%3D%20Unresolved%20AND%20component%20%3D%20Lint%20ORDER%20BY%20priority%20DESC

## Seems to be related, but in fact not

`dce` phase, this is not dead code warning part .... so not related in fact

- https://github.com/scala/scala/blob/2.11.x/src/compiler/scala/tools/nsc/backend/opt/DeadCodeElimination.scala
- https://github.com/scala/scala/blob/2.11.x/src/compiler/scala/tools/nsc/backend/icode/analysis/ReachingDefinitions.scala