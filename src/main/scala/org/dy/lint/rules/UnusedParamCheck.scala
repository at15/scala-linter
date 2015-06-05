package org.dy.lint.rules

import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent

/**
 * Created by Pillar on 2015/6/5.
 */
class UnusedParamCheck(val global:Global) extends PluginComponent{
  println("UnusedParamCheckComponent loaded!")

  import global._

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
