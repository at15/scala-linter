// scala has a lot of pattern match

// TODO: e... these pattern match all got error ...
//def printArr(arr:Array[Int]):String = {
//  case Array(0) => "0"
//  case Array(x:Int,y:Int) => x + "" + y
//  case Array(0,_*) => "0 ..."
//}

//def printList(lst:List[Int]):String = {
//  case 1 => "I won't match" // this is dead code
//  case 0 :: Nil => "0"
//  case x :: y :: Nil => x + " " + y
//  case _ => "something else"
//}

val (x,y) = (1,2)
// use pattern match in for
import collection.JavaConversions.propertiesAsScalaMap
for((k,v) <- System.getProperties){
  println(k + " -> " + v)
}

// use match
for((k1,"") <- System.getProperties){
  println(k1)
}

// use gard with match
for((k,v) <- System.getProperties if v != "SUN_STANDARD" && v != "CN"){
  println(v)
}

// usage of @ operator, bind the nested value to variable
