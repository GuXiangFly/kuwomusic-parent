package com.kuwomusic.springbootkuwomusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.kuwomusic.springbootkuwomusic.mapper")
public class SpringbootKuwomusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKuwomusicApplication.class, args);
    }

}
