package org.dy.lint.rules

import scala.reflect.internal.Flags

//import scala.reflect.internal.Definitions

import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent

import scala.collection.mutable.Set

class UnusedParamCheck(val global: Global) extends PluginComponent {
  println("UnusedParamCheckComponent loaded!")

  import global._

  val phaseName = "unused param check"
  override val description = "unuused param"
  // TODO:which phase should it runAfter...
  //  override val runsAfter = List("typer")
  override val runsAfter = List("refchecks")

  def newPhase(prev: Phase) = new UnusedParamCheckPhase(prev)

  class UnusedParamCheckPhase(prev: Phase) extends StdPhase(prev) {
    override def apply(unit: CompilationUnit): Unit = {
      if (!Config.isEnabled(UnusedParamCheck.this.phaseName)) {
        //        println("unused param check is not enabled, skipp")
        return
      }
      UnusedParamCheckTraverse.traverse(unit.body)
    }
  }

  // TODO:this is quite not functional...
  private val paramNames = Set[String]()

  private object UnusedParamCheckTraverse extends Traverser {
    println("Unused Param CheckTraverse!")

    override def traverse(tree: Tree): Unit = tree match {
      // TODO:ignore others
      // TODO:abstract is not working.
      // TODO:scape goat is using
      // https://github.com/at15/scalac-scapegoat-plugin/issues/1
      //      case DefDef(mods, _, _, _, _, _) if mods.hasFlag(Flag.DEFERRED) => // ignore abstract methods
      // ignore traits, quite often you define a method in a trait with default impl that does nothing
      //      case ClassDef(_, _, _, _) if tree.symbol.isTrait                                  =>
      // ignore abstract methods obv.
      //      case DefDef(mods, _, _, _, _, _) if mods.hasFlag(Flag.ABSTRACT)                   =>
      case d@DefDef(_, _, _, _, _, _) if d.symbol != null && d.symbol.isAbstract =>
      // ignore constructors, those params become fields
      case DefDef(_, nme.CONSTRUCTOR, _, _, _, _) =>
      case DefDef(_, _, _, _, _, _) if tree.symbol != null && tree.symbol.isConstructor =>
      //      case DefDef(_, _, _, _, tpt, _) if tpt.tpe =:= NothingTpe                         =>
      // ignore overriden methods, the parameter might be used by other classes
      case DefDef(mods, _, _, _, _, _) if mods.isOverride ||
        mods.hasFlag(Flags.OVERRIDE) ||
        (tree.symbol != null && (tree.symbol.isAnyOverride || tree.symbol.isOverridingSymbol)) =>
      //      case v @ ValDef(mods, _, _, valDefs, _, rhs) => {
      ////        println(mods)
      ////        println(mods.isDeferred)
      ////        println(showRaw(mods))
      //        show(valDefs)
      //        // get all params, it's a list of ValDef
      //        paramNames.clear()
      ////        for (valDef <- valDefs) {
      ////          for (parameter <- valDef) {
      ////            paramNames.add(parameter.name.toString)
      ////          }
      ////        }
      ////        MethodBodyTraverse.traverse(tree)
      ////        if (!paramNames.isEmpty) for (paramName <- paramNames) global.reporter.warning(tree.pos, "[Unused param] " + paramName)
      //      }
      case d@DefDef(mods, _, _, valDefs, _, rhs) => {
        println(mods)
        println(mods.isDeferred)
        println(showRaw(mods))
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
