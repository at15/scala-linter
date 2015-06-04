# Scala tree

http://docs.scala-lang.org/overviews/reflection/symbols-trees-types.html

tree seems to be more useful for dead code detection

- [x] what is `::` used in the example pattern matching
- [ ] what is `Select`
- [ ] what is `Indent`
- [x] what is `@` used in traverser

#### ::  (collection operation)

when `Apply(fn,arg :: Nil)` we get arg == 3, for x.$plus(3), when use `Apply(fn,arg)` we get arg == List(3)

copied from `playground/collection.sc`, :: means add a element in head of a List, so `arg :: Nil` can match a List which
only has one element, and `arg` is that element. In scala a empty List is Nil, and every List a head and tail, while tail
is another list, for List that only have one element, its tail is nail, so we can use `arg :: Nil` to get `3` from `List(3)`

````
val la =List("a")
val lb = "b" :: List("a")
val (d :: e) = lb
val (f :: Nil) = la
````
#### Select

````
 /** An extractor class to create and pattern match with syntax `Select(qual, name)`.
   *  This AST node corresponds to the following Scala code:
   *
   *    qualifier.selector
   *
   *  Should only be used with `qualifier` nodes which are terms, i.e. which have `isTerm` returning `true`.
   *  Otherwise `SelectFromTypeTree` should be used instead.
   *
   *    foo.Bar // represented as Select(Ident(<foo>), <Bar>)
   *    Foo#Bar // represented as SelectFromTypeTree(Ident(<Foo>), <Bar>)
   *  @group Extractors
   */
  abstract class SelectExtractor {
    def apply(qualifier: Tree, name: Name): Select
    def unapply(select: Select): Option[(Tree, Name)]
  }
````

#### Ident 

`Apply(Select(Ident(TermName("x")), TermName("$plus")), List(Literal(Constant(2))))`
`x.$plus(2)`

identical `=` ? no ... seems

````
 /** An extractor class to create and pattern match with syntax `Ident(qual, name)`.
   *  This AST node corresponds to the following Scala code:
   *
   *    name
   *
   *  Type checker converts idents that refer to enclosing fields or methods to selects.
   *  For example, name ==> this.name
   *  @group Extractors
   */
  abstract class IdentExtractor {
    def apply(name: Name): Ident
    def unapply(ident: Ident): Option[Name]
  }
````  

#### @ 

usage of @ operator, bind the nested value to variable

sample code from scala for the impatient

````
abstract class Item
case class Article(desc: String, price: Double) extends Item
case class Bundle(desc: String, discount: Double, items: Item*) extends Item

Bundle("Father's day special",20.0,
    Article("Scala for the impatient",39.95),
    Bundle("Anchor Distillery Sampler", 10.0,
        Article("Old Potrero Straight Rye Whisky", 79.95),
        Article("Junipero Gin", 32.95)))

case Bundle(_, _, art @ Article(_, _), rest @ _*) => ...

// a sample usage
def price(it: Item): Double = it match {
    case Article(_,p) => p
    case Bundle(_, disc, its @ _*) => its.map(price _).sum - disc
}
````