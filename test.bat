@echo off
echo "Run test on the new plugin"
echo "Copy the jar "
copy /y target\scala-2.11\scala-linter_2.11-0.0.1.jar example\
echo "Run all the tests"
node example/test.js
