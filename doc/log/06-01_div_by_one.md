# Detect division by one

We got division by zero, so if we change `Constant(0)` -> `Constant(1)`, it should show warning for 
division by one, but it didn't show
````
def div() {
    val a = 2 / 1  // no warning for div by one
    2 / 0  // div by zero warning 
}
````

so why? Is it the plugins' problem or something else.

The fact is when you are traversing( or pattern matching) the tree after parse. `2 / 1` is `2` already.
The parser has done the calculation for the constants.

But if you do the following 
````
def div() {
    val a = 2 / 1  // no warning for div by one
    val c = a / 1 // this will show warning
    2 / 0  // div by zero warning 
}
````

You can know what the tree is by using reflection api.

````
import scala.reflect.runtime.universe._
val divByZero = reify {2/0}
println(showRaw(divByZero.tree))

val divByOne = reify {val x=3; x/1}
println(showRaw(divByOne.tree))
````

More about tree

http://docs.scala-lang.org/overviews/reflection/symbols-trees-types.html