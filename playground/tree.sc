import scala.reflect.runtime.universe._

val tree = Apply(Select(Ident(TermName("x")), TermName("$plus")), List(Literal(Constant(2))))
tree.toString()

val expr = reify { class Flower { def name = "Rose" } }
expr.tree
println(showRaw(expr.tree))

val divByZero = reify {2/0}
println(showRaw(divByZero.tree))

val divByOne = reify {val x=3; x/1}
println(showRaw(divByOne.tree))