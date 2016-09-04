# spark-wordcount
Wordcount demo for Apache Spark

## Requirements

To build and run this application, you must have installed:
- Scala 2.11.X
- SBT
- Apache Spark locally or in a cluster you want to deploy the application

## How to build
Firstly, you must clone the project from GitHub in a folder. If you're using Linux and your folder is encrypted,
you must clone it into a folder in /, (prefer shorter names to this folder). In addition, you need to set

```{scala}
scalacOptions ++= Seq("-Xmax-classfile-name","100")
```
inside *~.sbt/0.13/local.sbt*.

After that, enter in the project's folder and build the project with the following command:

```{bash}
sbt clean compile assembly
```

## How to run locally

In first place, you must create an **input folder**, where you should place [the Shakespeare corpus](https://github.com/andretadeu/spark-wordcount/raw/master/src/main/resources/shakespeare.txt).
 After that, you must start the master by running *start-master.sh* script. Once the master is running, open the URL
 http://localhost:8080/ to discover the URL of the master, so you can run the slave by running the script
 *start-slave.sh [Apache Spark master URL]*.

Now you're ready to effectively run the application. To do this, just run the following script:

```{bash}
spark-submit --class WordCount --master <Master URL> <local of the fat jar>/spark-wordcount-assembly-1.0.jar <input folder> <output folder>
```
