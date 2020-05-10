package com.kuwomusic.springbootkuwomusic.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalysisServiceTest {

    @Autowired
    AnalysisService analysisService;

    @Test
    public void getMusicTypeResult() {
        Object test = analysisService.getMusicTypeResult("test");
        System.out.println(test);
    }
}
