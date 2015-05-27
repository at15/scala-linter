@echo off
echo "Run test on the new plugin"
echo "Copy the file "
copy /y target\scala-2.11\scala-linter_2.11-0.0.1.jar example\
echo "Compile with the plugin "
cd example
scalac -Xplugin:scala-linter_2.11-0.0.1.jar unused.scala
scala -cp . Unused
cd ..