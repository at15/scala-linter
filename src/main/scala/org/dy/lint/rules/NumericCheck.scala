package org.dy.lint.rules


import scala.tools.nsc._
import scala.tools.nsc.plugins.PluginComponent

/**
 * Created by Pillar on 2015/6/5.
 */
class NumericCheck(val global: Global) extends PluginComponent {
  println("NumericCheckComponent loaded!")

  import global._

  val phaseName = "numeric check"
  override val description = "numeric operation that cause dead code"
  // TODO:which phase?
  //    override val runsAfter = List("refchecks")
  override val runsAfter = List("parser") // TODO:this is also ok

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
