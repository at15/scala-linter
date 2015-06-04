import scala.reflect.runtime.universe._

val expr = reify {
  def bark(name:String): Unit ={
    println("a")
  }
}

print(show(expr.tree))
print(showRaw(expr.tree))
