import scala.reflect.runtime.universe._

val expr = reify {
  def bark(name: String, date: String): Unit = {
    println(date)
  }
}

print(show(expr.tree))
print(showRaw(expr.tree))
object tt extends Traverser {
  override def traverse(tree: Tree): Unit = tree match {
    case DefDef(mods, _, _, valDefs, _, body) => {
      println(show(valDefs))
      // get the name from it.
//      println(valDefs.flatMap().map(_.name.toString))
    }
    case _ => super.traverse(tree)
  }
}

tt.traverse(expr.tree)
