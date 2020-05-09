package com.kuwomusic.springbootkuwomusic.repository;

import com.kuwomusic.springbootkuwomusic.entity.AnalysisResultInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisResultInfoRepository extends JpaRepository<AnalysisResultInfoEntity,Integer> {

    AnalysisResultInfoEntity findAnalysisResultInfoEntityByResultType(String resultType);
}