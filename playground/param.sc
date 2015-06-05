import scala.reflect.runtime.universe._
import scala.collection.mutable.ArrayBuffer
val expr = reify {
//  def bark(name: String, date: String = "213"): Unit = {
  def bark(name: String, date: String): Unit = {
    val z = 2
    val doubi = name + "is a doubi"
    println(date)
  }
}
print(show(expr.tree))
print(showRaw(expr.tree))
// use gloabl var ?
object paramNameTraver extends Traverser {
  override def traverse(tree:Tree) = tree match {
    case Ident(TermName(name)) => {
      println("I got " + name)
    }
    case _ => super.traverse(tree)
  }
}
def paramUsedInBody(paramName: String, tree: Tree) = {
  println("check if " + paramName + " is used!")
  // TODO:need traverse?
//  tree match {
//    //    case Ident(TermName(name)) if name == paramName => {
//    case Ident(TermName(name)) => {
//      println("I got " + name)
//    }
//    case _ => // just for avoid cache
//  }
  paramNameTraver.traverse(tree)

  println(showRaw(tree))
}

object tt extends Traverser {
  override def traverse(tree: Tree): Unit = tree match {
    case d@DefDef(mods, _, _, valDefs, _, body) => {
      val paramNames = ArrayBuffer[String]()
      //      println("This is d!")
      //      println(show(d))
      //      println("These are valDefs!")
      //      println(show(valDefs))
      // get the name from it.
      for (valDef <- valDefs) {
        //        println(show(valDef))
        for (v <- valDef) {
          //          println(show(v))
          paramNames.append(v.name.toString)
        }
      }

      // TODO: only need to traverse the tree once.
      for (paramName <- paramNames) {
        paramUsedInBody(paramName, tree)
      }
    }
    case _ => super.traverse(tree)
  }
}
tt.traverse(expr.tree)
