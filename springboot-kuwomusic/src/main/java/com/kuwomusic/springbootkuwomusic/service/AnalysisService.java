package com.kuwomusic.springbootkuwomusic.service;


//import com.kuwomusic.springbootkuwomusic.repository.AnalysisResultInfoRepository;
//import com.kuwomusic.springbootkuwomusic.repository.HotWordInfoRepository;
import com.alibaba.fastjson.JSON;
import com.kuwomusic.springbootkuwomusic.mapper.AnalysisResultInfoMapper;
import com.kuwomusic.springbootkuwomusic.mapper.HotWordInfoMapper;
import com.kuwomusic.springbootkuwomusic.mapper.MusicInfoMapper;
import com.kuwomusic.springbootkuwomusic.pojo.AnalysisResultInfo;
import com.kuwomusic.springbootkuwomusic.pojo.HotWordInfo;
import com.kuwomusic.springbootkuwomusic.pojo.MusicInfo;
import com.kuwomusic.springbootkuwomusic.pojo.SingerTypeAndLikeWeight;
import com.kuwomusic.springbootkuwomusic.pojo.TypeAndValueBo;
import com.kuwomusic.springbootkuwomusic.response.HistogramResponseBo;
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

    @Autowired
    private MusicInfoMapper musicInfoMapper;



    public Object getAnalysisResultByQuery(String query){
        if ("musicLyricHotWord".equals(query)){
            List<HotWordInfo> hotWordInfos = hotWordInfoMapper.selectAllByHotWordType("歌词热词");
            return hotWordInfos.get(0);
        }
        if ("musicCommentHotWord".equals(query)){
            List<HotWordInfo> hotWordInfos = hotWordInfoMapper.selectAllByHotWordType("评论热词");
            return hotWordInfos.get(0);
        }
        return  null;
    }



    public Object getMusicTypeResult(String query){
        List<AnalysisResultInfo> analysisResultInfos = analysisResultInfoMapper.selectAllByResultType(query);
        if (analysisResultInfos.isEmpty()){
            return null;
        }
        AnalysisResultInfo analysisResultInfo = analysisResultInfos.get(0);
        String resultJson = analysisResultInfo.getResultJson();

        if ("歌手类型热度".equals(query)){
            List<SingerTypeAndLikeWeight> singerTypeAndLikeWeights = JSON.parseArray(resultJson, SingerTypeAndLikeWeight.class);
            HistogramResponseBo histogramResponseBo = new HistogramResponseBo();
            for (SingerTypeAndLikeWeight singerTypeAndLikeWeight : singerTypeAndLikeWeights) {
                histogramResponseBo.getTypeList().add(singerTypeAndLikeWeight.getSinger_type());
                histogramResponseBo.getValueList().add(singerTypeAndLikeWeight.getLikeWeight());
            }
            return  histogramResponseBo;
        }


        List<TypeAndValueBo> typeAndValueBos = JSON.parseArray(resultJson, TypeAndValueBo.class);
        HistogramResponseBo histogramResponseBo = new HistogramResponseBo();
        for (TypeAndValueBo typeAndValueBo : typeAndValueBos) {
            histogramResponseBo.getTypeList().add(typeAndValueBo.getTypeName());
            histogramResponseBo.getValueList().add(typeAndValueBo.getHotValue());
        }
        return  histogramResponseBo;
    }


    public Object getMusicTop30Comment(){
        List<MusicInfo> musicInfos = musicInfoMapper.selectCommentTop30();
        return musicInfos;
    }
}
