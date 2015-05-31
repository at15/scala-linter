object Lack{
  def main(arg:Array[String]) = {
    println("This what scala lack")
  }

  def silentBranch(){
    if(true){
      println("I am always executing")
    }else{
      println("I am dead code but you never knows")
    }
  }

  def advancedSilentBranch(doOrDie:Boolean){
    if(doOrDie){
      println("Do!")
      if(!doOrDie){
        println("I will never execute")
      }
    }
  }
}
