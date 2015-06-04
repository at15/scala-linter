import scala.reflect.runtime.universe._

class Mie {
  def add(): Unit ={

  }
}

val c = new Mie

showRaw(reify(c.add()).tree)
showRaw(reify( { val x = 2 }).tree)
showRaw(reify({"x".startsWith("a")}).tree)

val d = "abc"
showRaw(reify({d.startsWith("a")}).tree)

val e = "abd"
showRaw(reify({e.startsWith("a")}).tree)

val x = 1
showRaw(reify({x/1}).tree)