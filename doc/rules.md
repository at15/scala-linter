# Rules that this linter support

## Numeric operations check

code that has no impact to final result

- div by one `val x = 1; x / 1` always return x
- mod by one `val x = 1; x % 1` always return 0

don't contribute to the result but cause exception

- div by zero `val x = 1; x /0`, `2 / 0` 
- mod by zero `val x = 1; x % 0`, `2 % 0`