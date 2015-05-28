# How to use Intellij idea

## Open existing projects

- clone the repo
- open the cloned folder eg: `E:\repo\linter`
- find the `build.sbt` or `Build.scala`
- open the file, and idea will show a hint on top of the editor
- click the `refresh project` and after some download you will see the whole project

## Open project using ipr file

Some project like https://github.com/scala/scala already has `ipr` file.

> The following steps are required to use IntelliJ IDEA on Scala trunk

> - Run ant init. This will download some JARs to ./build/deps, which are included in IntelliJ's classpath.
- Run src/intellij/setup.sh
- Open ./src/intellij/scala.ipr in IntelliJ
- File, Project Settings, Project, SDK. Create an SDK entry named "1.6" containing the Java 1.6 SDK. (You may use a later SDK for local development, but the CI will verify against Java 6.)

Intellij will show hint for configure jdk if you don't specific manually.
And run all the command in bash, if you use cmd, you can't execute `sh` files.

## Create new project

- File -> New Project -> SBT

## Build package

- opend cmd, change to the project root (the folder which contains `project` and `target`)
- run `sbt package`
- built `jar` will be `target\scala-2.11\project-name-version.jar`
