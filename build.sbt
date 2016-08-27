name := "spark-wordcount"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.0.0",
  "org.apache.spark" % "spark-hive_2.11" % "2.0.0"
    exclude("org.apache.parquet", "parquet-format")
    exclude("org.datanucleus", "datanucleus-api-jdo")
    exclude("org.datanucleus", "datanucleus-rdbms"),
  "com.databricks" % "spark-csv_2.11" % "1.4.0")

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "twitter", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", "parquet", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("org", "aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("org", "datanucleus", xs @ _*) => MergeStrategy.last
  case PathList("org", "objenesis", xs @ _*) => MergeStrategy.last
  case PathList("parquet", "org", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}