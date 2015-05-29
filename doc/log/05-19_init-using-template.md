# Init compiler plugin project

Using template https://github.com/jrudolph/scalac-plugin.g8

I didn't use g8, just create a SBT project using IDEA, and copy the compiler related code

- the folder don't need to follow the package name. ie: you don't need three depth folder for `org.dy.linter`
- the example plugin in the template is a transformer, while most other plugins seems to be working on phase
- set all the `phaseName` and `name` to same string. I could simply use `DummyPlugin.this.name`