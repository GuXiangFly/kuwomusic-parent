package com.kuwomusic.springbootkuwomusic.service;


//import com.kuwomusic.springbootkuwomusic.repository.AnalysisResultInfoRepository;
//import com.kuwomusic.springbootkuwomusic.repository.HotWordInfoRepository;
import com.kuwomusic.springbootkuwomusic.mapper.AnalysisResultInfoMapper;
import com.kuwomusic.springbootkuwomusic.mapper.HotWordInfoMapper;
import com.kuwomusic.springbootkuwomusic.pojo.AnalysisResultInfo;
import com.kuwomusic.springbootkuwomusic.pojo.HotWordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author guxiang02
 * @Date 2020/5/9
 **/
@Service
public class AnalysisService {


    @Autowired
    private AnalysisResultInfoMapper analysisResultInfoMapper;

    @Autowired
    private HotWordInfoMapper hotWordInfoMapper;



    public Object getAnalysisResultByQuery(String query){
        if (query == "musicLyricHotWord"){
            List<HotWordInfo> hotWordInfos = hotWordInfoMapper.selectAllByHotWordType("歌词热词");
            return hotWordInfos.get(0);
        }
        if (query == "musicCommentHotWord"){
            List<HotWordInfo> hotWordInfos = hotWordInfoMapper.selectAllByHotWordType("评论热词");
            return hotWordInfos.get(0);
        }

        if (query == "singerType"){
            List<AnalysisResultInfo> analysisResultInfos = analysisResultInfoMapper.selectAllByResultType("歌手类型热度");
            return analysisResultInfos.get(0);
        }

        return  null;
    }
}
