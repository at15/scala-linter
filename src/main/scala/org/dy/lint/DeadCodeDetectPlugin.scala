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
  val components = List[PluginComponent](NumericCheckComponent,ReturnCheckComponent)

  private object NumericCheckComponent extends PluginComponent {
    println("NumericCheckComponent loaded!")

    import global._

    val global = DeadCodeDetectPlugin.this.global
    val phaseName = "numeric check"
    override val description = "numeric operation that cause dead code"
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
    import  global._
    val global = DeadCodeDetectPlugin.this.global
    val phaseName = "return check"
    override val description = "methods that return constant might be dead code"
    // TODO: whcih phase should it runsAfter, I am not sure ....
    override val runsAfter = List("parser")
    def newPhase(prev: Phase) = new ReturnCheckPhase(prev)
    class ReturnCheckPhase(prev:Phase) extends StdPhase(prev){
      override def apply(unit: CompilationUnit): Unit = {
          ReturnConstantCheckTraverse.traverse(unit.body)
      }

      private object ReturnConstantCheckTraverse extends Traverser{
        override def traverse(tree: Tree): Unit = tree match {
          // TODO:remove b and other debug code
          case DefDef(_,_,_,_,_,Literal(Constant(a))) =>
            global.reporter.warning(tree.pos,"[ReturnCheck] constant " + a + " is returned")
//            println("return constant!" + a)
//            println("This is b\r\n" + showRaw(b) )
            super.traverse(tree) // TODO: do we need this here?
          case _ => super.traverse(tree)
        }
      }
    }

  }

  private object ReplaceStringComponent extends PluginComponent with Transform {
    println("ReplaceStringComponent")

    import global._

    val global = DeadCodeDetectPlugin.this.global
    val phaseName = DeadCodeDetectPlugin.this.name
    override val runsAfter = List("parser")

    def newTransformer(unit: CompilationUnit) = new DeadCodeDetectTransformer(unit)

    class DeadCodeDetectTransformer(unit: CompilationUnit) extends Transformer {

      override def transform(tree: Tree): Tree = tree match {
        case Literal(Constant(str: String)) => {
          println("oh la la I am a sting")
          global.reporter.warning(tree.pos, "always error!")
          //          Literal(Constant("ICanHazYourStrngLiterls"))
          Literal(Constant(str))
        }

        // don't forget this case, so that tree is actually traversed
        case _ => super.transform(tree)
      }
    }

  }

}