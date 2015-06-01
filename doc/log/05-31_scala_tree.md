# Scala tree

http://docs.scala-lang.org/overviews/reflection/symbols-trees-types.html

tree seems to be more useful for dead code detection

- [x] what is `::` used in the example pattern matching
- [ ] what is `Select`
- [ ] what is `Indent`
- [ ] what is `@` used in traverser

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