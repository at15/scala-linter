import scala.reflect.runtime.universe._
val expr = reify {
  abstract class A{
    def abmethod(a:String):Int
    def n(a:String):Unit = {
      println(a)
    }
  }
}
println(show(expr.tree))
println(showRaw(expr.tree))
class Mie{
  val say = (name:String) => {
    println("a")
  }
}
val c = new Mie
c.say("b")