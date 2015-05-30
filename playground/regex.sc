//val s = "[warn] E:\\lab\\scala\\lint-me\\src\\main\\scala\\org\\dy\\lint\\Person.scala:3: imported `Cat' is permanently hidden by definition of class Cat in package lint"
val s = "[warn] E:\\lab\\scala\\lint-me\\src\\main\\scala\\org\\dy\\lint\\Cat.scala:11: dead code following this construct"
val reg = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*)".r
val reg(fileName,lineNumber,msg) = s
fileName
lineNumber
msg
//reg.findAllIn(s).group(0)
//val numitemPattern = "([0-9]+) (.*)".r
//val numitemPattern(num, item) = "99 bottles"
//num
//item

val t = "[warn]     return \"a\"// Dead Code: dead code following this construct"
t.substring(7)