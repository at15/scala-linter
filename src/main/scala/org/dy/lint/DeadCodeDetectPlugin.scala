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
  val components = List[PluginComponent](DivisionByZeroComponent, ReplaceStringComponent)


  private object ReplaceStringComponent extends PluginComponent with Transform {

    import global._

    val global = DeadCodeDetectPlugin.this.global
    val phaseName = DeadCodeDetectPlugin.this.name
    override val runsAfter = List("parser")

    def newTransformer(unit: CompilationUnit) = new DeadCodeDetectTransformer(unit)

    class DeadCodeDetectTransformer(unit: CompilationUnit) extends Transformer {

      override def transform(tree: Tree): Tree = tree match {
        case Literal(Constant(str: String)) => Literal(Constant("ICanHazYourStrngLiterls"))

        // don't forget this case, so that tree is actually traversed
        case _ => super.transform(tree)
      }
    }
  }


  private object DivisionByZeroComponent extends PluginComponent {

    import global._

    val global = DeadCodeDetectPlugin.this.global
    val phaseName = "division by zero"
    override val runsAfter = List("refchecks")

    def newPhase(prev: Phase) = new DivisionByZeroPhase(prev)

    class DivisionByZeroPhase(prev: Phase) extends StdPhase(prev) {
      // TODO:what is unit?
      override def apply(unit: CompilationUnit) {
        for (tree@Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) <- unit.body;
             if rcvr.tpe <:< definitions.IntClass.tpe) {
          global.reporter.warning(tree.pos, "definitely division by zero")
        }

//        for (tree@Apply(Select(rcvr, nme.DIV), List(Literal(Constant(1)))) <- unit.body;
//             if rcvr.tpe <:< definitions.IntClass.tpe) {
//          global.reporter.warning(tree.pos, "definitely division by one")
//        }
      }
    }

  }

}