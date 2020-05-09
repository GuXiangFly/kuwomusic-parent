package com.kuwomusic.springbootkuwomusic.pojo;

import lombok.Data;

@Data
public class AnalysisResultInfo {
    private Integer id;

    private String resultType;

    private String resultJson;
}