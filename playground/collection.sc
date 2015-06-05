val la = List("a")
val lb = "b" :: List("a")
val (d :: e) = lb
val (f :: Nil) = la
// test map
val l1 = List("Mie", "Ayi", "qiAn")
l1.map(_.toUpperCase())
for (n <- l1) yield n.toUpperCase()
//def ulcase(s: String) = Vector(s.toUpperCase(), s.toLowerCase())
def ulcase(s: String) = List(s.toUpperCase(), s.toLowerCase())
l1.map(ulcase(_))
l1.flatMap(ulcase(_))
import collection.mutable.Set
import collection.mutable.ArrayBuffer
val bf = ArrayBuffer[String]()
bf.append("jack")
bf.append("Jimmy")
bf.indexOf("jack")
// not working...
val h = bf.drop(bf.indexOf("jimmy"))
h
val paramNames = Set("a","b","c")
val leftNames = paramNames -- Set("a")
leftNames.isEmpty



