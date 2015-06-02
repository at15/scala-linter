# Tree structure for methods

````
val expr = reify {
  def s() = {
    3
  }
}
````

````
````
{
  def s() = 3;
  ()
}
````
Block(List(DefDef(Modifiers(), TermName("s"), List(), List(List()), TypeTree(), Literal(Constant(3)))), Literal(Constant(())))
// Block is for {}
List(DefDef(Modifiers(), TermName("s"), List(), List(List()), TypeTree(), Literal(Constant(3)))), Literal(Constant(()))
// Literal(Constant(())) is for the finishing () TODO:what's its usage?
List(DefDef(Modifiers(), TermName("s"), List(), List(List()), TypeTree(), Literal(Constant(3))))
// List is just a list I suppose, remove it TODO:why we have it?
DefDef(Modifiers(), TermName("s"), List(), List(List()), TypeTree(), Literal(Constant(3)))
// DefDef is for `def` and Literal(Constant(3)) is for the return value 3
// so the pattern should be
List(DefDef(_,_,_,_,),_,Literal(Constant(returnValue)))
````