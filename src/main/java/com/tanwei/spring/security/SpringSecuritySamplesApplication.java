package com.tanwei.spring.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class SpringSecuritySamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecuritySamplesApplication.class, args);
    }

}
