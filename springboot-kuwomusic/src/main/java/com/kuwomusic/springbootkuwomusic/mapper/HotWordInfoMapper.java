package com.kuwomusic.springbootkuwomusic.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.kuwomusic.springbootkuwomusic.pojo.HotWordInfo;

@Mapper
public interface HotWordInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HotWordInfo record);

    int insertSelective(HotWordInfo record);

    HotWordInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotWordInfo record);

    int updateByPrimaryKey(HotWordInfo record);

    List<HotWordInfo> selectAllByHotWordType(@Param("hotWordType")String hotWordType);

}