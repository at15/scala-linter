# Rules that this linter support

# Unused parameters

enable it by adding `-P:DeadCodeDetect:unused_param_check` to `scalacOptions`

- unused parameters in methods or functions `def hi(a:String,b:String){println("hi")}` 

# Return 

enable it by adding `-P:DeadCodeDetect:return_check` to `scalacOptions`

- return constant `def a ={val x = 1;2}` 

# If 

enable it by adding `-P:DeadCodeDetect:if_check` to `scalacOptions`

- always false `if(false)` quite silly.

## Numeric operations 

enable it by adding `-P:DeadCodeDetect:numeric_check` to `scalacOptions`

code that has no impact to final result

- div by one `val x = 1; x / 1` always return x
- mod by one `val x = 1; x % 1` always return 0

don't contribute to the result but cause exception

- div by zero `val x = 1; x /0`, `2 / 0` 
- mod by zero `val x = 1; x % 0`, `2 % 0`