package com.kuwomusic.springbootkuwomusic.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.kuwomusic.springbootkuwomusic.pojo.AnalysisResultInfo;

@Mapper
public interface AnalysisResultInfoMapper {
    int insert(AnalysisResultInfo record);

    int insertSelective(AnalysisResultInfo record);

    List<AnalysisResultInfo> selectAllByResultType(@Param("resultType")String resultType);


}