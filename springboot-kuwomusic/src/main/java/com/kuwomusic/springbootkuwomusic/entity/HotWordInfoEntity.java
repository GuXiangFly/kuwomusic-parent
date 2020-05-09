package com.kuwomusic.springbootkuwomusic.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "analysis_result_info")
public class HotWordInfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "result_type")
    private Long resultType;


    @Column(name = "result_json")
    private java.util.Date resultJson;

}