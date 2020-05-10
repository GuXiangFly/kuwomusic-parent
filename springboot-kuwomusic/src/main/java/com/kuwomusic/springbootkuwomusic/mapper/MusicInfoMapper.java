package com.kuwomusic.springbootkuwomusic.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.kuwomusic.springbootkuwomusic.pojo.MusicInfo;

public interface MusicInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MusicInfo record);

    int insertSelective(MusicInfo record);

    MusicInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MusicInfo record);

    int updateByPrimaryKey(MusicInfo record);

    List<MusicInfo> selectCommentTop30();




}