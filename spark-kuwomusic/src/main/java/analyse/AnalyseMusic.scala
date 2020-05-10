package analyse

import java.util

import com.google.gson.Gson
import common.Constants
import common.model.{AnalysisResultInfo, MusicTimeDistributionBo, SingerTypeAndFavourably}
import dao.AnalysisResultInfoDAO
import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object AnalyseMusic {
  def singerAnalyse(spark: SparkSession): Unit = {
    val frame: DataFrame = spark.sql("select  singer_type,count(*) from singer_info group by singer_type")



  }


  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL_HIVE_Demo")

    val spark: SparkSession = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config(config)
      .enableHiveSupport()
      .getOrCreate()


    val musicTimeDistributionList = new util.ArrayList[MusicTimeDistributionBo]()
    musicTimeDistributionList.add(singerMusicTime(spark,Constants.MUSIC_0_3));
    musicTimeDistributionList.add(singerMusicTime(spark,Constants.MUSIC_3_4));
    musicTimeDistributionList.add(singerMusicTime(spark,Constants.MUSIC_4_5));
    musicTimeDistributionList.add(singerMusicTime(spark,Constants.MUSIC_5_6));
    musicTimeDistributionList.add(singerMusicTime(spark,Constants.MUSIC_6_));


    val gson = new Gson()
    val str: String = gson.toJson(musicTimeDistributionList)

    AnalysisResultInfoDAO.insertOrUpdate(new AnalysisResultInfo(2,"歌曲时长热度",str))
    println("歌曲时长分析：",str)
  }




  def singerMusicTime(spark: SparkSession, musicTimeLine: String): MusicTimeDistributionBo = {

    var sql = "";

    if (Constants.MUSIC_0_3.equals(musicTimeLine)){
      sql = "select SumComment(music_comment_count) from music_info where music_duration_second=>0 and  music_duration_second <180;"
    }
    if (Constants.MUSIC_3_4.equals(musicTimeLine)){
      sql = "select SumComment(music_comment_count) from music_info where music_duration_second=>180 and  music_duration_second <240;"
    }
    if (Constants.MUSIC_4_5.equals(musicTimeLine)){
      sql = "select SumComment(music_comment_count) from music_info where music_duration_second=>240 and  music_duration_second <300;"
    }
    if (Constants.MUSIC_5_6.equals(musicTimeLine)){
      sql = "select SumComment(music_comment_count) from music_info where music_duration_second=>300 and  music_duration_second <360;"
    }
    if (Constants.MUSIC_6_.equals(musicTimeLine)){
      sql = "select SumComment(music_comment_count) from music_info where music_duration_second=>360;"
    }

    import spark.implicits._
    //创建RDD
    val udaf = new SumCommentFunction

    spark.udf.register("SumComment", udaf)
    val frame: DataFrame = spark.sql(sql)
    frame.show()
    val rows: Array[Row] = frame.rdd.collect()

    val musicTimeDistributionBo = new MusicTimeDistributionBo(musicTimeLine, rows.toList(0).getDouble(0).toInt)
    println(musicTimeDistributionBo)
    musicTimeDistributionBo
  }


  class SumCommentFunction extends UserDefinedAggregateFunction {

    //函数输入的数据结构
    override def inputSchema: StructType = {
      new StructType().add("music_comment_count",LongType)
    }

    //函数计算的数据类型
    override def bufferSchema: StructType = {
      new StructType().add("sum",LongType).add("count",LongType)
    }

    //函数返回的数据类型
    override def dataType: DataType = DoubleType


    override def deterministic: Boolean = true
    //计算之前的缓冲区初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0) = 0L //sum
      buffer(1) = 0L //count
    }

    //根据查询的结果 输入的值  来更新我们的缓存区
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      //sum
      buffer(0) =  buffer.getLong(0) + input.getLong(0)
      //count
      buffer(1) = buffer.getLong(1) +1
    }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      //sum
      buffer1(0) =  buffer1.getLong(0) + buffer2.getLong(0)
      //count
      buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
    }

    override def evaluate(buffer: Row): Any = {
      val result =  buffer.getLong(0)
      return result.toDouble
    }
  }

}
