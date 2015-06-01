# Annotations names scope

> Arguments in scalaArgs are represented as typed trees.
> Note that these trees are not transformed by any phases following the type-checker

> Names are simple wrappers for strings. Name has two subtypes TermName and TypeName which distinguish
> names of terms (like objects or members) and types (like classes, traits, and type members)

### Standard name
http://www.scala-lang.org/api/2.11.6/scala-reflect/#scala.reflect.api.Universe@TermNamesApiextendsStandardNames.this.NamesApi

### Scope

> A scope object generally maps names to symbols available in a corresponding lexical scope

> Additional functionality is exposed in member scopes that are returned by members and declarations
> defined in scala.reflect.api.Types#TypeApi. scala.reflect.api.Scopes#MemberScope supports the sorted method,
> which sorts members in declaration order.

### Constants

> Certain expressions that the Scala specification calls constant expressions can be evaluated by the Scala compiler at compile time
> Constant expressions are used to represent literals in abstract syntax trees (see scala.reflect.api.Trees#Literal)
