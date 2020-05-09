package com.kuwomusic.springbootkuwomusic.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author guxiang02
 * @Date 2020/5/9
 **/
@Data
@AllArgsConstructor
public class ApiResult {
    Integer code;
    String message;
    Object data;


    public static ApiResult  success(Object data){
        return new ApiResult(0,"Success",data);
    }
}
