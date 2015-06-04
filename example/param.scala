abstract class UnusedParameter{
  def call(phone:String,name:String) = {
    println("call " + name)
  }

  def iamabstract(name:String)
}

class UnusedParameter2 extends UnusedParameter{
  def iamabstract(name:String) = {
    println("I just don't call the name haha")
  }
}