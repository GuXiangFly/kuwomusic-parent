package com.kuwomusic.springbootkuwomusic.entity;

import lombok.Data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author guxiang
 */
@Data
@Entity
@Table(name = "analysis_result_info")
public class AnalysisResultInfoEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "result_type")
    private Long resultType;


    @Column(name = "result_json")
    private java.util.Date resultJson;

}