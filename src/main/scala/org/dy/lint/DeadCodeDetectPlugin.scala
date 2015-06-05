package org.dy.lint

import scala.tools.nsc
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent

import org.dy.lint.rules.NumericCheck
import org.dy.lint.rules.ReturnCheck

class DeadCodeDetectPlugin(val global: Global) extends Plugin {

  import global._

  val name = "DeadCodeDetect"
  val description = "Dead Code Detection for scala"
  //  val components = List[PluginComponent](NumericCheckComponent, ReturnCheckComponent, IfCheckComponent, UnusedParamCheckComponent)
  val components = List[PluginComponent](NumericCheckComponent, ReturnCheckComponent, IfCheckComponent)

  private object NumericCheckComponent extends NumericCheck(global)

  private object ReturnCheckComponent extends ReturnCheck(global)

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

    val global = DeadCodeDetectPlugin.this.global
    val phaseName = "unused param check"
    override val description = "unuused param"
    // TODO:which
    override val runsAfter = List("refChecks")

    def newPhase(prev: Phase) = new UnusedParamCheckPhase(prev)

    class UnusedParamCheckPhase(prev: Phase) extends StdPhase(prev) {
      // TODO: real code here
      // TODO:what is Select and Ident
      //      Block(List(DefDef(Modifiers(), TermName("bark"), List(), List(List(ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree))), Ident(scala.Unit), Apply(Select(Ident(scala.Predef), TermName("println")), List(Literal(Constant("a")))))), Literal(Constant(())))
      override def apply(unit: CompilationUnit): Unit = {

      }

      // Block(List(DefDef(Modifiers(), TermName("bark"), List(), List(List(ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree), ValDef(Modifiers(PARAM), TermName("date"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree))), Ident(scala.Unit), Apply(Select(Ident(scala.Predef), TermName("println")), List(Literal(Constant("a")))))), Literal(Constant(())))res1: Unit = ()
      // Block(List(DefDef(Modifiers(), TermName("bark"), List(), List(List(ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree), ValDef(Modifiers(PARAM | DEFAULTPARAM/TRAIT), TermName("date"), Select(Ident(scala.Predef), TypeName("String")), Literal(Constant("213"))))), Ident(scala.Unit), Block(List(ValDef(Modifiers(), TermName("z"), TypeTree(), Literal(Constant(2))), ValDef(Modifiers(), TermName("doubi"), TypeTree(), Apply(Select(Ident(TermName("name")), TermName("$plus")), List(Literal(Constant("is a doubi")))))), Apply(Select(Ident(scala.Predef), TermName("println")), List(Ident(TermName("date")))))), DefDef(Modifiers(METHOD | SYNTHETIC | DEFAULTPARAM/TRAIT), TermName("bark$default$2"), List(), List(), Select(Ident(scala.Predef), TypeName("String")), Literal(Constant("213")))), Literal(Constant(())))res1: Unit = ()
      private object UnusedParamCheckTraverse extends Traverser {
        override def traverse(tree: Tree): Unit = tree match {
          // TODO:ignore others
          case d@DefDef(mods, _, _, valDefs, _, rhs) => {
            // get all params, it's a list of ValDef
          }
          case _ => super.traverse(tree)
        }
      }

    }

  }

}