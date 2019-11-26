package com.zgy.qshy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// @EnableSwagger2
@EnableCaching
@MapperScan("com.zgy.qshy.**.mapper")
public class UrlChangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlChangeApplication.class, args);
    }

}
