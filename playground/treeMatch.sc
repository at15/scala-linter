import scala.reflect.runtime.universe._

val tree = Apply(Select(Ident(TermName("x")), TermName("$plus")), List(Literal(Constant(2))))
tree.toString()
showRaw(tree)

// TODO: what is ::
val (fun,arg) = tree match {
  case Apply(fn, a :: nil) => (fn,a)
}

// This pattern matching is more clear
val Apply(fun2, arg2 :: Nil) = tree
fun2
arg2


val treeComplex = Apply(Select(Apply(Select(Ident(newTermName("x")), newTermName("$plus")), List(Literal(Constant(2)))), newTermName("$plus")), List(Literal(Constant(3))))
val Apply(fun3,arg3 :: Nil) = treeComplex
fun3
arg3

// so we need to use traverse to do better