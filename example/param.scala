abstract class UnusedParameter{
  def call(phone:String,name:String) = {
    println("call " + name)
  }

  def multipleUnused(p:String,s:String) = {
    println("no use no use la la la")
  }

  def iamabstract(name:String)

//  def iamabstract2(name:String)
}

class UnusedParameter2 extends UnusedParameter{
  def iamabstract(name:String) = {
    println("I just don't call the name haha")
  }

//  def iamabstract2(name:String) = "pia"
}