/**
 * Created by Pillar on 2015/5/22.
 */

object Unused {
  // param never used
  def paramUnused(x:Int) :Int = {
    1
  }

  // local var never used
  def localUnused : Int = {
    val x = 2
    3
  }

  // statement after return is never executed
  def afterReturn : Int ={
    return 1
    val x = 233
    x
  }

  // one branch will never get executed
  def silentBranch : Int ={
    if(1 > 0){
      2
    }else{
      val x = 233
      x
    }
  }

  // this one is harder to detect though ...
  def UselessValue : Int = {
    val x = 1
    val y = 2
    val z = x + y
    3
  }
}
