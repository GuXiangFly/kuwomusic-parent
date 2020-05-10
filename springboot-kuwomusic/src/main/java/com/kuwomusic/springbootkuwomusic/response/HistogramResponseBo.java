package com.kuwomusic.springbootkuwomusic.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author guxiang02
 * @Date 2020/5/10
 **/
@Data
public class HistogramResponseBo {
    List<String> typeList = new ArrayList<>();
    List<Integer> valueList = new ArrayList<>();
}
