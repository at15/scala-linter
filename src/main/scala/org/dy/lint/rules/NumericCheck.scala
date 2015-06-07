package org.dy.lint.rules

import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent

class NumericCheck(val global: Global) extends PluginComponent {

  import global._

  val phaseName = "numeric check"
  override val description = "numeric operation that cause dead code"
  // TODO:which phase?
  //    override val runsAfter = List("refchecks")
  override val runsAfter = List("parser") // TODO:this is also ok

  def newPhase(prev: Phase) = new NumericCheckPhase(prev)

  class NumericCheckPhase(prev: Phase) extends StdPhase(prev) {
    override def apply(unit: CompilationUnit) {
      if (!Config.isEnabled(NumericCheck.this.phaseName)) {
        //        println("numeric check is not enabled, skipp")
        return
      }
      for (tree@Apply(Select(receiver, operator), List(Literal(Constant(denominator)))) <- unit.body;
           if (receiver.tpe <:< definitions.IntClass.tpe) && (operator == nme.DIV || operator == nme.MOD)) {
        denominator match {
          // type 3, dead code with side effects
          case 0 => if (operator == nme.DIV)
            global.reporter.warning(tree.pos, "[numeric check] Div by zero")
          else global.reporter.warning(tree.pos, "[numeric check] Mod by zero")
          // type 2, code that don't contribute to final result
          case 1 => if (operator == nme.DIV)
            global.reporter.warning(tree.pos, "[numeric check] Div by one")
          else global.reporter.warning(tree.pos, "[numeric check] Mod by one")
        }
      }
    }
  }

}
