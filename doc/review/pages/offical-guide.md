# The offical guide for scala plugin

http://www.scala-lang.org/old/node/140

- really old ....
- but seems to be the only guide ...


## Terms

- phases

One step for the whole compile process?

see a pdf in ref `compiler-phase-sid.pdf`

**TODO: how many phases do we have and compiler api allow use to access**

## Article detail

the plugin is a jar at last, need to use `sbt package` to generate it.

> A plugin is a kind of compiler component that lives in a separate jar file from the main compiler. The compiler can then load that plugin and gain extra functionality.

````java
package localhost

import scala.tools.nsc
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent

class DivByZero(val global: Global) extends Plugin {
  import global._

  val name = "divbyzero"
  val description = "checks for division by zero"
  val components = List[PluginComponent](Component)

  private object Component extends PluginComponent {
    val global: DivByZero.this.global.type = DivByZero.this.global
    val runsAfter = "refchecks"
    // Using the Scala Compiler 2.8.x the runsAfter should be written as below
    // val runsAfter = List[String]("refchecks");
    val phaseName = DivByZero.this.name
    def newPhase(_prev: Phase) = new DivByZeroPhase(_prev)

    class DivByZeroPhase(prev: Phase) extends StdPhase(prev) {
      override def name = DivByZero.this.name
      def apply(unit: CompilationUnit) {
        for ( tree @ Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) <- unit.body;
             if rcvr.tpe <:< definitions.IntClass.tpe)
          {
            unit.error(tree.pos, "definitely division by zero")
          }
      }
    }
  }
}
````
> The plugin is described by a top-level class that inherits from Plugin, takes a Global as a constructor parameter, and exports that parameter as a val named global.

- the class takes global as param from constructor.

````java
// in scala, a val passed from constructor becomes class member automatically
class mie(val weight:Int){
  def yo = {
    this.weight // visit `weight`
  }
}
val a = new Mie(12)
a.weight // 12
````

> The plugin must define one or more component objects that inherits from PluginComponent. In this case the sole component is the nested Component object. The components of a plugin are listed in the components field.

````java
// PluginComponent is element type in the list, Component is the nested class in
// the following code
val components = List[PluginComponent](Component)

private object Component extends PluginComponent {
  // .. component code goes here
}
````

> Each component must define newPhase method that creates the component's sole compiler phase. That phase will be inserted just after the specified compiler phase, in this case refchecks.

- runsAfter list value, newPhase depend on what you are trying to do, for transformer, it would be `newTransformer`

````java
val global: DivByZero.this.global.type = DivByZero.this.global
val runsAfter = "refchecks"
// Using the Scala Compiler 2.8.x the runsAfter should be written as below
// val runsAfter = List[String]("refchecks");
val phaseName = DivByZero.this.name
def newPhase(_prev: Phase) = new DivByZeroPhase(_prev)
````

> Each phase must define a method apply that does whatever you desire on the given compilation unit. Usually this involves examining the trees within the unit and doing some transformation on the tree.

- TODO: tree,apply etc...

````java
def newPhase(_prev: Phase) = new DivByZeroPhase(_prev)

class DivByZeroPhase(prev: Phase) extends StdPhase(prev) {
  override def name = DivByZero.this.name
  def apply(unit: CompilationUnit) {
    for ( tree @ Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) <- unit.body;
         if rcvr.tpe <:< definitions.IntClass.tpe)
      {
        unit.error(tree.pos, "definitely division by zero")
      }
  }
}
````
