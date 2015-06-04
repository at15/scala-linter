
import scala.reflect.runtime.universe._

val expr = reify {
  if (true) {
    println("I am doubi")
  }
}
println(show(expr.tree))
println(showRaw(expr.tree))
expr.tree match {
  //  case If(Literal(Constant(v)),_*) => println("always " + v)
  case If(cond, thenp, elsep) => {
    println(cond)
    println(thenp)
    println(elsep)
  }
  case _ => "Not constant "
}