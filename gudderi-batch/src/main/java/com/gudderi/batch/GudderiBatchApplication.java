package com.gudderi.batch;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gudderi")
@MapperScan(value = "com.gudderi.batch.repository", annotationClass = Mapper.class)
public class GudderiBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(GudderiBatchApplication.class, args);
    }
}
