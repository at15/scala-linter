# Hacker guide

http://www.scala-lang.org/contribute/hacker-guide.html

- some useful hint
- some official resources

- [x] use `println` for degbug, it works for compiler plugin.
- [ ] print Stack trace
- [ ] showRaw to get Ast representation 

> Don’t underestimate the power of print. When starting with Scala, I spent a lot of time in the debugger trying to figure out how things work.
 However later I found out that print-based debugging is often more effective than jumping around.
  While it might be obvious to some, I’d like to explicitly mention that it’s also useful to print stack traces to understand the flow of execution. 
  When working with Trees, you might want to use showRaw to get the AST representation.
  
- [ ] http://docs.scala-lang.org/overviews/reflection/overview.html