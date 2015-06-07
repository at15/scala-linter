package org.dy.lint.rules

import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent

class ReturnCheck(val global: Global) extends PluginComponent {
  println("ReturnCheckComponent loaded!")

  import global._

  val phaseName = "return check"
  override val description = "methods that return constant might be dead code"
  // TODO: which phase should it runsAfter, I am not sure ....
  override val runsAfter = List("parser")

  def newPhase(prev: Phase) = new ReturnCheckPhase(prev)

  class ReturnCheckPhase(prev: Phase) extends StdPhase(prev) {
    override def apply(unit: CompilationUnit): Unit = {
      if(!Config.isEnabled(ReturnCheck.this.phaseName)){
        println("Return check is disabled, skipp")
        return
      }
      ReturnConstantCheckTraverse.traverse(unit.body)
    }

    private object ReturnConstantCheckTraverse extends Traverser {
      override def traverse(tree: Tree): Unit = tree match {
        // TODO:remove b and other debug code
        case DefDef(_, _, _, _, _, Literal(Constant(a))) =>
          global.reporter.warning(tree.pos, "[ReturnCheck] constant " + a + " is returned")
          super.traverse(tree) // TODO: even add the traverse, we still can't detect a nested one.
        case _ => super.traverse(tree)
      }
    }

  }

}
