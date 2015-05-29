// simple print
println("I know you were trouble when you writing code")

// print stack trace
// TODO: we can only print stack tree when there is an exception?
/* try{
  val a = 123
  throw new IllegalArgumentException("test")
}catch {
  case ex: IllegalAccessError => ex.printStackTrace()
}
*/

// showRaw
// http://www.scala-lang.org/files/archive/nightly/docs/library/index.html#scala.reflect.api.Printers

import scala.reflect.runtime.universe._
def tree = reify{ final class C { def x = 2 } }.tree
// worksheet is printing nothing..., scala is also not printing...
// only repl can print? ... dumb, show and showRaw return string and print nothing
println(show(tree))
println(showRaw(tree))
