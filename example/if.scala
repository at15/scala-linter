/**
 * Created by Pillar on 2015/6/2.
 */
object ifCheck {
  def constantFalse = {
    if (false) {
      println("You know I will get you, yeah, what ever it takes, to get there")
    }
  }

  def nestedConstantFalse = {
    if (false) {
      println("Ayi ya yi ya yi a")
      if (false) {
        println("Nested Ayi ya yi ya yi a")
      }
    }
  }

  def silentBranch(a: Boolean) = {
    if (a) {
      println("One I just want to be with you")
      if (!a) {
        println("Two you don't want to be with me")
      }
    }
  }

  // TODO:Repeated condition
  def repeatedCondition = {
    //    val a = true
    //    val b = false
    //    if(a || b ){
    //      val c = 4
    //    }else if(a && b)
    //    if(a == 10 || b == 10) 0 else if(a == 20 && b == 10) 1 else 2
  }
}
