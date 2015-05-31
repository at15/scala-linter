# Compiler plugin

## Reading list

- [ ] Tree,traverse http://docs.scala-lang.org/overviews/reflection/symbols-trees-types.html
- [ ] DefDef,PreDef,Constant etc. http://docs.scala-lang.org/overviews/reflection/annotations-names-scopes.html

TODO:
- [ ] compile process
- [ ] common terms
- [ ] dead code detection method
- [ ] type1(unreachable) dead code detection
- [ ] unused variable detection

## show phases

`scalac -Xshow-phases`
`scalac -Xshow-phases -Xplugin:scala-linter_2.11-0.0.1.jar`

The second one will show phases added by the plugin.
