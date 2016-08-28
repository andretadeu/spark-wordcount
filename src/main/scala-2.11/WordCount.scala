import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions.{desc, explode, lower, regexp_replace, split, trim}

object WordCount {

  def removePunctuation(column: Column) = {
    val columnWithoutPunctuation = regexp_replace(column, """([^\w\d\s]|_)+""", "")
    val columnTrimmed = trim(columnWithoutPunctuation)
    val lowercasedColumn = lower(columnTrimmed)
    lowercasedColumn.alias("clean_sentence")
  }

  def lineToWords(shakespeareDF: DataFrame) = {
    shakespeareDF.filter("clean_sentence != ''")
      .select(
        explode(
          split(shakespeareDF("clean_sentence"), """\s+""")
        ).alias("word"))
  }

  def wordCount(wordListDF: DataFrame) = {
    wordListDF.groupBy("word").count()
  }

  def main(args: Array[String]) {
    val warehouseLocation = "file:${system:user.dir}/spark-warehouse"
    if (args.length < 2) {
      System.err.println("You must provide the input folder and the output folder")
      System.exit(1)
    }
    val spark = SparkSession
      .builder()
      .appName("WordCount")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()
    val shakespeareDF = spark.read.text(args(0) + "/shakespeare.txt")
    val cleanSentencesColumn = removePunctuation(shakespeareDF("value"))
    val shakeWordsDF = lineToWords(shakespeareDF.select(cleanSentencesColumn))
    val result = wordCount(shakeWordsDF).orderBy(desc("count"))
    result.write.format("com.databricks.spark.csv").option("header", "true").save(args(1) + "/wordcount")
  }
}
