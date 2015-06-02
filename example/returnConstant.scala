/**
 * Created by Pillar on 2015/6/2.
 */
object ReturnConstant {
  def simple () = {
    1
  }

  def simple2() = {
    val a = 1
    "I am a string!"
  }

  def nestedReturnConstant() = {
    // TODO: the nested one is not discovered
    def IamTheNested() = {
      1
    }
    2
  }

  def notDeadCode( name:String):Int = {
    if(name == "jack"){
      return 1
    }else{
      return 2
    }
  }
}
