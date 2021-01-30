package com.z.shiro_study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr zhang
 */
@SpringBootApplication
@MapperScan("com.z.shiro_study.dao")
public class ShiroStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroStudyApplication.class, args);
    }

}
