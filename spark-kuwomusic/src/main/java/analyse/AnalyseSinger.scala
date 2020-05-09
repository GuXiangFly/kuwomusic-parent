package analyse

import java.util
import com.google.gson.Gson
import common.model.{AnalysisResultInfo, SingerTypeAndFavourably}
import dao.AnalysisResultInfoDAO
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object AnalyseSinger {
  def singerAnalyse(spark: SparkSession): Unit = {
    val frame: DataFrame = spark.sql("select  singer_type,count(*) from singer_info group by singer_type")


  }


  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL_HIVE_Demo")

    val favourablies = new util.ArrayList[SingerTypeAndFavourably]()

    val spark: SparkSession = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config(config)
      .enableHiveSupport()
      .getOrCreate()

    favourablies.add(singerSqlAction(spark, "欧美女"))
    favourablies.add(singerSqlAction(spark, "华语女"))
    favourablies.add(singerSqlAction(spark, "欧美男"))
    favourablies.add(singerSqlAction(spark, "日韩女"))
    favourablies.add(singerSqlAction(spark, "日韩组合"))
    favourablies.add(singerSqlAction(spark, "华语男"))
    favourablies.add(singerSqlAction(spark, "华语组合"))
    favourablies.add(singerSqlAction(spark, "日韩男"))

    val gson = new Gson()
    val str: String = gson.toJson(favourablies)

    AnalysisResultInfoDAO.insertOrUpdate(new AnalysisResultInfo(1,"歌手类型热度",str))
    println(str)
  }


  def singerSqlAction(spark: SparkSession, singer_type: String): SingerTypeAndFavourably = {
    val sql =
      """
        |select sum(tempSinger.fans_count)/50
        |from (
        |         select * from singer_info where singer_type = '?' order by fans_count desc limit 50
        |     ) as tempSinger
        |""".stripMargin.replace("?", singer_type)

    val frame: DataFrame = spark.sql(sql)
    frame.show()
    val rows: Array[Row] = frame.rdd.collect()

    val favourably = new SingerTypeAndFavourably(singer_type, rows.toList(0).getDouble(0).toInt)
    println(favourably)
    favourably

  }
}
