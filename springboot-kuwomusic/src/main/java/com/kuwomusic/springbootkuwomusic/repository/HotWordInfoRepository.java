package com.kuwomusic.springbootkuwomusic.repository;

import com.kuwomusic.springbootkuwomusic.entity.AnalysisResultInfoEntity;
import com.kuwomusic.springbootkuwomusic.entity.HotWordInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotWordInfoRepository extends JpaRepository<HotWordInfoRepository,Integer> {

    HotWordInfoEntity findHotWordInfoRepositoryByResultType(String resultType);
}