package org.dy.lint.rules

import scala.collection.mutable

/**
 * Created by Pillar on 2015/6/7.
 */
object Config {
  val enabledRules = mutable.Set[String]()
  // TODO:change rules to object ? in order to get phaseName
  val allRules = Set(
    "if check",
    "numeric check",
    "return check",
    "unused param check"
  )

  // TODO:currently we are using phaseName to represent each rules, which is a bad idea...
  def enable(rule: String) = {
    //    println("need to enable rule!" + rule)
    if (allRules.contains(rule)) {
      enabledRules.add(rule)
    } else {
      // TODO:warn here
      println("[warn] rule " + rule + "is not in available rules")
    }
  }

  def disable(rule: String) = {
    // TODO:deal with all option.
    enabledRules.remove(rule)
  }

  def isEnabled(rule: String): Boolean = {
    //    println("check if is enabled!" + enabledRules)
    enabledRules.contains(rule)
  }
}
