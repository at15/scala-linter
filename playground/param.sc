import scala.reflect.runtime.universe._
import scala.collection.mutable.ArrayBuffer
val expr = reify {
  def bark(name: String, date: String = "213"): Unit = {
    val z = 2
    val doubi = name + "is a doubi"
    println(date)
  }
}
print(show(expr.tree))
print(showRaw(expr.tree))
object tt extends Traverser {
  override def traverse(tree: Tree): Unit = tree match {
    case d @ DefDef(mods, _, _, valDefs, _, body) => {
      val paramNames = ArrayBuffer[String]()
      println("This is d!")
      println(show(d))
      println("These are valDefs!")
      println(show(valDefs))
      // get the name from it.
//      println(valDefs.flatMap().map(_.name.toString))
      // ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")
      for(valDef <- valDefs){
        println(show(valDef))
        for( v <- valDef){
          println(show(v))
          paramNames.append(v.name.toString)
        }
      }

//      val valDef = valDefs.take(1)
//      for( v <- valDef){
//        println(show(v))
//      }
      println("These are param names!")
      println(paramNames)
    }
    case _ => super.traverse(tree)
  }
}
tt.traverse(expr.tree)
