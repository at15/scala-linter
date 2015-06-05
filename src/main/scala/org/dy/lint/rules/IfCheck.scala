package org.dy.lint.rules


import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent

/**
 * Created by Pillar on 2015/6/5.
 */
class IfCheck(val global: Global) extends PluginComponent{
  println("IfCheckComponent loaded!")

  import global._

  val phaseName = "if check"
  override val description = "if branches that will never execute"
  // TODO: which phase should it runsAfter, I am not sure ....
  override val runsAfter = List("parser")

  def newPhase(prev: Phase) = new IfCheckPhase(prev)

  class IfCheckPhase(prev: Phase) extends StdPhase(prev) {
    override def apply(unit: CompilationUnit): Unit = {
      IfCheckTraverse.traverse(unit.body)
    }

    private object IfCheckTraverse extends Traverser {
      override def traverse(tree: Tree): Unit = tree match {
        // TODO:other check
        case If(cond, thenp, elsep) =>
          if (cond.toString() == "false")
            global.reporter.warning(tree.pos, "[IfCheck] condition is always false")
          super.traverse(tree) // TODO: detect nested one?  yes , but tree is bfs right ?... e... confused
        case _ => super.traverse(tree)
      }
    }

  }
}
