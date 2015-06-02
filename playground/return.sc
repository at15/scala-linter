import scala.reflect.runtime.universe._

// for #4

val expr = reify {
  def s() = {
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
    case List() => {
      println("list!")
    }
    case _ => {
      println("oh la la")
      show(tree)
      showRaw(tree)
      super.traverse(tree)
    }
  }
}

traverser.traverse(expr.tree)
