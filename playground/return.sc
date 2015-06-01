import scala.reflect.runtime.universe._

// for #4

val expr = reify {
  def s():Int = {
    val a = 2
    3
  }
}

show(expr.tree)
showRaw(expr.tree)
expr.tree.pos
