package org.dy.lint.rules

import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent

import scala.collection.mutable.Set

class UnusedParamCheck(val global: Global) extends PluginComponent {
  println("UnusedParamCheckComponent loaded!")

  import global._

  val phaseName = "unused param check"
  override val description = "unuused param"
  // TODO:which phase should it runAfter...
  override val runsAfter = List("parser")

  def newPhase(prev: Phase) = new UnusedParamCheckPhase(prev)

  class UnusedParamCheckPhase(prev: Phase) extends StdPhase(prev) {
    //      Block(List(DefDef(Modifiers(), TermName("bark"), List(), List(List(ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree))), Ident(scala.Unit), Apply(Select(Ident(scala.Predef), TermName("println")), List(Literal(Constant("a")))))), Literal(Constant(())))
    override def apply(unit: CompilationUnit): Unit = {
      UnusedParamCheckTraverse.traverse(unit.body)
    }
  }

  // TODO:this is quite not functional...
  private val paramNames = Set[String]()

  // Block(List(DefDef(Modifiers(), TermName("bark"), List(), List(List(ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree), ValDef(Modifiers(PARAM), TermName("date"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree))), Ident(scala.Unit), Apply(Select(Ident(scala.Predef), TermName("println")), List(Literal(Constant("a")))))), Literal(Constant(())))res1: Unit = ()
  // Block(List(DefDef(Modifiers(), TermName("bark"), List(), List(List(ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")), EmptyTree), ValDef(Modifiers(PARAM | DEFAULTPARAM/TRAIT), TermName("date"), Select(Ident(scala.Predef), TypeName("String")), Literal(Constant("213"))))), Ident(scala.Unit), Block(List(ValDef(Modifiers(), TermName("z"), TypeTree(), Literal(Constant(2))), ValDef(Modifiers(), TermName("doubi"), TypeTree(), Apply(Select(Ident(TermName("name")), TermName("$plus")), List(Literal(Constant("is a doubi")))))), Apply(Select(Ident(scala.Predef), TermName("println")), List(Ident(TermName("date")))))), DefDef(Modifiers(METHOD | SYNTHETIC | DEFAULTPARAM/TRAIT), TermName("bark$default$2"), List(), List(), Select(Ident(scala.Predef), TypeName("String")), Literal(Constant("213")))), Literal(Constant(())))res1: Unit = ()
  private object UnusedParamCheckTraverse extends Traverser {
    println("Unused Param CheckTraverse!")

    override def traverse(tree: Tree): Unit = tree match {
      // TODO:ignore others
      // TODO:abstract is not working.
      case DefDef(mods, _, _, _, _, _) if mods.hasFlag(Flag.ABSTRACT) => // ignore abstract methods
      case DefDef(mods, _, _, valDefs, _, rhs) => {
        // get all params, it's a list of ValDef
        paramNames.clear()
        for (valDef <- valDefs) {
          for (parameter <- valDef) {
            paramNames.add(parameter.name.toString)
          }
        }
        MethodBodyTraverse.traverse(tree)
        if (!paramNames.isEmpty) for (paramName <- paramNames) global.reporter.warning(tree.pos, "[Unused param] " + paramName)
      }
      case _ => super.traverse(tree)
    }
  }

  private object MethodBodyTraverse extends Traverser {
    override def traverse(tree: Tree): Unit = tree match {
      case Ident(TermName(name)) if (paramNames.contains(name)) => paramNames.remove(name) // remove the used
      case _ if paramNames.isEmpty => // all param are used, no need to traverse
      case _ => super.traverse(tree)
    }
  }

}
