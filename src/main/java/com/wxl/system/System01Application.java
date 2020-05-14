package com.wxl.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.wxl.system.dao")
public class System01Application {
    public static void main(String[] args) {
        SpringApplication.run(System01Application.class, args);
    }
}
