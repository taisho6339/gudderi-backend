package com.gudderi.api;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.gudderi")
@MapperScan(value = "com.gudderi.api.repository", annotationClass = Mapper.class)
public class GudderiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GudderiApplication.class, args);
    }
}
