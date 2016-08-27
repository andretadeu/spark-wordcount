name := "spark-wordcount"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.0.0",
  "org.apache.spark" % "spark-hive_2.11" % "2.0.0",
  "com.databricks" % "spark-csv_2.11" % "1.4.0")