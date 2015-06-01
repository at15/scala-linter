# The report for kc-3g

- Use scalac built in lint and parse log
- Write custom linter

## Use scalac build in lint

`-Ywarn-dead-code` `-Ywarn-unused` and parse warning log

https://github.com/at15/scalac-log-formatter


However, scalac built in warning is not enough and has wrong warning, ie `yield`

[lack](compiler/warning.md)

## Write custom plugin

this repo

dead code

1. statement after return (improve scalac's?)
2. silent if branch (scalac doesn't support)
3. *pattern that will never match (dont know yet)

unused

1. unused local val,var (scalac didn't support #4, when constant is returned)
2. unused param (scala-abide and many other linter already support it)
3. constant is returned for function. (but this is really hard ... though)

useless operations

1. division by 1.
2. calculation that has no impact on result.

I guess this is enough now.
