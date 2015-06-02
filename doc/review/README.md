# Review for resources

- [pages](pages)
- [projects](projects)

TODO: list important ones here

http://docs.scala-lang.org/overviews/reflection/symbols-trees-types.html

http://docs.scala-lang.org/overviews/reflection/annotations-names-scopes.html

## Useful rules in other projects

### scala-abide

##### Unused local definitions https://github.com/scala/scala-abide/

name : **unused-member**
source : [UnusedMember](/rules/core/src/main/scala/com/typesafe/abide/core/UnusedMember.scala)

Definitions that cannot be accessed outside of the current scope should _always_ be used in the current scope. If this is not the case, then these definitions shouldn't exist as they reduce code readability by bloating the current scope.

This rule applies to:
- private class members
- non-overriding method arguments
- block-local values and definitions

scalac has the first and third, but don't check unused arguments

### scala-goat https://github.com/sksamuel/scalac-scapegoat-plugin

TODO:inspect the code

##### ConstantIf
##### DivideByOne
##### ImpossibleOptionSizeCondition
..but I dont know what is option,some scala specific term? ...
##### ModOne
Checks for x % 1 which will always return 0
##### RepeatedCaseBody
Checks for case statements which have the same body
##### UnnecessaryReturnUse


These might be useful

- EmptyIfBlock
- EmptyMethod
- EmptyFor
- EmptyWhileBlock
- WhileTrue

### linter

##### If checks

- Repeated condition in an else-if chain ` if(a == 10 || b == 10) 0 else if(a == 20 && b == 10) 1 else 2`
- Identical branches `if(b > 4) (2,a) else (2,a)`
- Unnecessary if `if(a==b) true else false`

##### pattern match

- Detect some unreachable cases `(x,y) match { case (a,5) if a > 5 => 0 case (c,5) if c > 5 => 1 }`
- Identical neighbouring cases `a match { case 3 => "hello" case 4 => "hello" case 5 => "hello" case _ => "how low" }

##### Unused method parameters

`def func(b: Int, c: String, d: String) = { println(b); b+c }`

Might be useful one

https://github.com/HairyFotr/linter#integer-checks-some-abstract-intepretation