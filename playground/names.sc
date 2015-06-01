import scala.reflect.runtime.universe._
val mapName = TermName("map")

val listTpe = typeOf[List[Int]]
listTpe.member(mapName)

// To search for a type member, one can follow the same procedure, using newTypeName instead
// e... no example

class Jack{
  class Mary{

  }
}

val classTpe = typeOf[Jack]
val className = TypeName("class")
classTpe.members

// oops, can't use member with className ... e

val overridden = listTpe.decls.sorted