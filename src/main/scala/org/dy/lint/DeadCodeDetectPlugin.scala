package org.dy.lint


import scala.tools.nsc
import scala.tools.nsc._
import scala.tools.nsc.plugins._
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent
import nsc.transform.{ Transform, TypingTransformers }
import nsc.symtab.Flags

//
//class DeadCodeDetectPlugin(val global: Global) extends Plugin {
//
//  import global._
//
//  val name = "DeadCodeDetect"
//  val description = "Dead Code Detection for scala"
////  val components = List[PluginComponent](DivisionByZeroComponent,ReplaceStringComponent)
//  val components = List[PluginComponent](ReplaceStringComponent)
//
//
//  private object ReplaceStringComponent extends PluginComponent with Transform {
//
//    import global._
//    import global.definitions._
//
//    val global = DeadCodeDetectPlugin.this.global
//    val phaseName = "replace string"
//    override val runsAfter = List("parser")
//
//    def newTransformer(unit: CompilationUnit) = new DeadCodeDetectTransformer(unit)
//
//    class DeadCodeDetectTransformer(unit: CompilationUnit) extends Transformer {
//
//      // TODO: fill in your logic here
//      override def transform(tree: Tree): Tree = tree match {
//        case Literal(Constant(str: String)) => Literal(Constant("ICanHazYourStrngLiterls"))
//
//        // don't forget this case, so that tree is actually traversed
//        case _ => super.transform(tree)
//      }
//    }
//  }
//
//
//
//  private object DivisionByZeroComponent extends PluginComponent {
//
//    import global._
//
//    val global = DeadCodeDetectPlugin.this.global
//    val phaseName = "division by zero"
//    override val runsAfter = List("refchecks")
//
//    def newPhase(prev: Phase) = new DivisionByZeroPhase(prev)
//
//    class DivisionByZeroPhase(prev: Phase) extends StdPhase(prev) {
//      // TODO:what is unit?
//      override def apply(unit: CompilationUnit) {
//        for (tree@Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) <- unit.body;
//             if rcvr.tpe <:< definitions.IntClass.tpe) {
//          global.reporter.warning(tree.pos, "definitely division by zero")
//        }
//
//                  for (tree@Apply(Select(rcvr, nme.DIV), List(Literal(Constant(1)))) <- unit.body;
//                       if rcvr.tpe <:< definitions.IntClass.tpe) {
//                    global.reporter.warning(tree.pos, "definitely division by one")
//                  }
////                  super.traverse(tree)
//      }
//    }
//
//  }
//
//}

class DeadCodeDetectPlugin(val global: Global) extends Plugin {
  import global._ // TODO: import what compiling code has imported?

  val name = "DeadCodeDetect"
  val description = "Dead Code Detection for scala"
  val components = List[PluginComponent](ReplaceStringComponent)

  // a sample component which is a transformer
  // which replaces all literal string constants
  // in the compiled sources
  private object ReplaceStringComponent extends PluginComponent with Transform {

    import global._
    import global.definitions._

    val global = DeadCodeDetectPlugin.this.global
    override val runsAfter = List("parser")
    // TODO:It must be the same as Plugin name ... I don't know why though.
    val phaseName = "DeadCodeDetect"

    def newTransformer(unit: CompilationUnit) = new DeadCodeDetectTransformer(unit)

    class DeadCodeDetectTransformer(unit: CompilationUnit) extends Transformer {

      // TODO: fill in your logic here
      override def transform(tree: Tree): Tree = tree match {
        case Literal(Constant(str: String)) => Literal(Constant("ICanHazYourStrngLiterls"))

        // don't forget this case, so that tree is actually traversed
        case _ => super.transform(tree)
      }
    }
  }
}



