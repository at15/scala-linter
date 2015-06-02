@echo off
echo "Run test on the new plugin"
echo "Copy the file "
copy /y target\scala-2.11\scala-linter_2.11-0.0.1.jar example\
echo "Compile with the plugin "
cd example
echo "Compile the unused.scala with linter"
scalac -Xplugin:scala-linter_2.11-0.0.1.jar unused.scala
echo "oops the bat can't continue to next scalac command, but i don't know why.... maybe turn into sh and run in git bash"
echo "Compile the numeric.scala with linter"
scalac -Xplugin:scala-linter_2.11-0.0.1.jar numeric.scala
