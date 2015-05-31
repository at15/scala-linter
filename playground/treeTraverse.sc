// Traverser makes sure to visit every node in a given tree, in a breadth-first search.
// Traverser is bfs

import scala.reflect.runtime.universe._

val tree = Apply(Select(Apply(Select(Ident(TermName("x")), TermName("$plus")), List(Literal(Constant(2)))), TermName("$plus")), List(Literal(Constant(3))))

object traverser extends Traverser {
  // intend to construct a list of Apply nodes that we find in our given tree
  var applies = List[Apply]()

  override def traverse(tree: Tree): Unit = tree match {
    case app@Apply(fun, args) =>
      applies = app :: applies
      // we want to make sure that we use the default traverse method in Traverser
      super.traverse(fun)
      super.traverseTrees(args)
    case _ => super.traverse(tree)
  }
}

traverser.traverse(tree)
traverser.applies

// e... kind of get it 