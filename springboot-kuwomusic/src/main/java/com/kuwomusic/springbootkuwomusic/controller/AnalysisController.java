package com.kuwomusic.springbootkuwomusic.controller;

import com.kuwomusic.springbootkuwomusic.response.ApiResult;
import com.kuwomusic.springbootkuwomusic.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guxiang02
 * @Date 2020/5/9
 **/
@RestController
public class AnalysisController {

    @Autowired
    AnalysisService analysisService;

    @GetMapping("/getAnalysisResult")
    public ApiResult getAnalysisResult(String query){
        Object analysisResultByQuery = analysisService.getAnalysisResultByQuery(query);
        return ApiResult.success(analysisResultByQuery);
    }

}
