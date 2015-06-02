import scala.reflect.runtime.universe._

// for #4

val expr = reify {
  def s() = {
    val x = 1
    3
  }
}
show(expr.tree)
showRaw(expr.tree)
expr.tree.pos
expr.tree match {
//  case List(DefDef(_,_,_,_,),_,Literal(Constant(returnValue))) => println("got u")
  case _ => "This match won't work" + show(expr.tree)
}
// need to use traverse
object traverser extends Traverser {
  override def traverse(tree: Tree): Unit = tree match {
//    // TODO: not enough pattern for class ModifiersExtractor offering
//    case DefDef(Modifiers, TermName("s"), List(), List(List()), TypeTree(), Literal(Constant(3))) =>
//      println("got you")
    case b@DefDef(_,_,_,_,_,Literal(Constant(a))) =>
      println("return constant!" + a)
      println("This is b\r\n" + showRaw(b) )
    case Literal(Constant(a)) => {
      println("I got " + a)
    }
    case _ => {
//      println("oh la la")
//      println(show(tree))
      println("\r\n")
      println(showRaw(tree))
      super.traverse(tree)
    }
  }
}

traverser.traverse(expr.tree)
