abstract class UnusedParameter{
  def call(phone:String,name:String) = {
    println("call " + name)
  }

  def multipleUnused(p:String,s:String) = {
    println("no use no use la la la")
  }

  def iamabstract(name:String)

  val valMethod = (name:String) => {
    println("aaa")
  }
//  def iamabstract2(name:String)
}

class UnusedParameter2 extends UnusedParameter{
  def iamabstract(name:String) = {
    println("I just don't call the name haha")
  }

//  def iamabstract2(name:String) = "pia"
}