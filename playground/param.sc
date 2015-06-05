import scala.reflect.runtime.universe._

val expr = reify {
  def bark(name: String, date: String = "213"): Unit = {
    val z = 2
    println(date)
  }
}
print(show(expr.tree))
print(showRaw(expr.tree))
object tt extends Traverser {
  override def traverse(tree: Tree): Unit = tree match {
    case d @ DefDef(mods, _, _, valDefs, _, body) => {
      println("This is d!")
      println(show(d))
      println("This is valDefs!\r\n")
      println(show(valDefs))
      // get the name from it.
//      println(valDefs.flatMap().map(_.name.toString))
      // ValDef(Modifiers(PARAM), TermName("name"), Select(Ident(scala.Predef), TypeName("String")
      for(valDef <- valDefs){
        println(show(valDef))
        for( v <- valDef){
          println(show(v))
        }
      }

//      val valDef = valDefs.take(1)
//      for( v <- valDef){
//        println(show(v))
//      }
    }
    case _ => super.traverse(tree)
  }
}
tt.traverse(expr.tree)
