package org.dy.lint

import scala.tools.nsc
import nsc.Global
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent

import org.dy.lint.rules.Config
import org.dy.lint.rules.NumericCheck
import org.dy.lint.rules.ReturnCheck
import org.dy.lint.rules.IfCheck
import org.dy.lint.rules.UnusedParamCheck

class DeadCodeDetectPlugin(val global: Global) extends Plugin {

  import global._

  val name = "DeadCodeDetect"
  val description = "Dead Code Detection for scala"
  val components = List[PluginComponent](
    NumericCheckComponent,
    ReturnCheckComponent,
    IfCheckComponent,
    UnusedParamCheckComponent
  )

  override def init(options: List[String], error: String => Unit): Boolean = {
    println("I am processing options!")
    for(option <- options){
      println("I got " + option)
      if(option.startsWith("enable")){
        // TODO: phase name has space currently, but you can have space in command line, so use _ and replace it with
        // space when process the options....
        Config.enable(option.substring("enable:".length).replace("_"," ").toLowerCase)
      }else if(option.startsWith("disable")){
        Config.disable(option.substring("disable:".length).replace("_"," ").toLowerCase)
      }
    }
    true
  }

  private object NumericCheckComponent extends NumericCheck(global)

  private object ReturnCheckComponent extends ReturnCheck(global)

  private object IfCheckComponent extends IfCheck(global)

  private object UnusedParamCheckComponent extends UnusedParamCheck(global)
}