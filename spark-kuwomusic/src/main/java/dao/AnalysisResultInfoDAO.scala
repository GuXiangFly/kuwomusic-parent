package dao

import common.CreateMySqlPool
import common.model.{AnalysisResultInfo, HotWordInfo}

object AnalysisResultInfoDAO {



  def insertOrUpdate(analysisResultInfo:AnalysisResultInfo) {

    val sql = "update analysis_result_info set result_json = ? where result_type = ?"

    // 获取对象池单例对象
    val mySqlPool = CreateMySqlPool()
    // 从对象池中提取对象
    val client = mySqlPool.borrowObject()

    val  updateParams: Array[Any] = Array(analysisResultInfo.result_json, analysisResultInfo.result_type)
    // 执行批量插入操作
    client.executeUpdate(sql, updateParams)


    // 使用完成后将对象返回给对象池
    mySqlPool.returnObject(client)
  }

}
