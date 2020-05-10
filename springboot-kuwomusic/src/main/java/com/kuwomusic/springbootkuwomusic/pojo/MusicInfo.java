package com.kuwomusic.springbootkuwomusic.pojo;

import lombok.Data;

@Data
public class MusicInfo {
    private Integer id;

    private String musicName;

    private String singerName;

    private String musicDurationMinute;

    private Integer musicDurationSecond;

    private String musicUrl;

    private String musicUrlId;

    private String musicLyric;

    private Integer musicCommentCount;

    private String musicComment;
}