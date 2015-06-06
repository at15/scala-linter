# Tool usage problems

### java.lang.OutOfMemoryError: GC overhead limit exceeded

when compile scala source using sbt.

**solution**

windows

change `C:\Program Files\sbt\sbtconfig.txt`. change all the size to 1024.

ref https://github.com/ochrons/scalajs-spa-tutorial/issues/11