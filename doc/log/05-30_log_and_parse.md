# Log compiler warning to file and then parse

- [x] Use `++=Seq[String]("-Yxxx","-Yxxx") to add multiple commandline options
- [x] ~~https://github.com/x3ro/scala-sbt-logback-example log to file,~~ this is for standalone log, not log redirect
- [x] use `sbt package > a.txt' to redirect output to file is enough.
- [x] ~~https://github.com/lihaoyi/fastparse for log parsing~~
- [x] read log line by line and output
 
Got a finished version, kind of cheating ....  https://github.com/at15/scalac-log-formatter

- [x] use regexp to get the line number and file name
- [x] output in plain text

further 

- [ ] make it a sbt plugin task, so we can `sbt mark-dead-code`
- [ ] output in other format, html, markdown
- [ ] change the source code ( auto fixer)
- [ ] show result in tree view ( need to change a lot of code I suppose)


 

