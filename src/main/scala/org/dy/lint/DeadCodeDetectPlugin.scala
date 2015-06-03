package org.dy.lint

import scala.tools.nsc
import scala.tools.nsc._
import scala.tools.nsc.plugins._
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent
import nsc.transform.{Transform, TypingTransformers}
import nsc.symtab.Flags


class DeadCodeDetectPlugin(val global: Global) extends Plugin {

  import global._

  val name = "DeadCodeDetect"
  val description = "Dead Code Detection for scala"
  val components = List[PluginComponent](NumericCheckComponent, ReturnCheckComponent, IfCheckComponent, UnusedParamCheckComponent)

  private object NumericCheckComponent extends PluginComponent {
    println("NumericCheckComponent loaded!")

    import global._

    val global = DeadCodeDetectPlugin.this.global
    val phaseName = "numeric check"
    override val description = "numeric operation that cause dead code"
    // TODO:which phase?
    override val runsAfter = List("refchecks")

    def newPhase(prev: Phase) = new NumericCheckPhase(prev)

    class NumericCheckPhase(prev: Phase) extends StdPhase(prev) {
      // TODO:what is ComilationUnit
      // TODO:add mod by one at same time
      override def apply(unit: CompilationUnit) {
        for (tree@Apply(Select(receiver, operator), List(Literal(Constant(denominator)))) <- unit.body;
             if (receiver.tpe <:< definitions.IntClass.tpe) && (operator == nme.DIV || operator == nme.MOD)) {
          denominator match {
            // type 3, dead code with side effects
            case 0 => if (operator == nme.DIV)
              global.reporter.warning(tree.pos, "[NumericCheck] Div by zero")
            else global.reporter.warning(tree.pos, "[NumericCheck] Mod by zero")
            // type 2, code that don't contribute to final result
            case 1 => if (operator == nme.DIV)
              global.reporter.warning(tree.pos, "[NumericCheck] Div by one")
            else global.reporter.warning(tree.pos, "[NumericCheck] Mod by one")
          }
        }
      }
    }

  }

  private object ReturnCheckComponent extends PluginComponent {
    println("ReturnCheckComponent loaded!")

    import global._

    val global = DeadCodeDetectPlugin.this.global
    val phaseName = "return check"
    override val description = "methods that return constant might be dead code"
    // TODO: which phase should it runsAfter, I am not sure ....
    override val runsAfter = List("parser")

    def newPhase(prev: Phase) = new ReturnCheckPhase(prev)

    class ReturnCheckPhase(prev: Phase) extends StdPhase(prev) {
      override def apply(unit: CompilationUnit): Unit = {
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

  private object IfCheckComponent extends PluginComponent {
    println("IfCheckComponent loaded!")

    import global._

    val global = DeadCodeDetectPlugin.this.global
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

  private object UnusedParamCheckComponent extends PluginComponent {
    println("UnusedParamCheckComponent loaded!")
    import global._
    val gloabal = DeadCodeDetectPlugin.this.global
    val phaseName = "unused param check"
    override val description = "unuused param"
    // TODO:which
    override val runsAfter = List("refChecks")

    def newPhase(prev:Phase) = new UnusedParamCheckPhase(prev)

    class UnusedParamCheckPhase(prev:Phase) extends StdPhase(prev){
      // TODO: real code here
    }
  }
}