package com.zgy.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@MapperScan("com.zgy.**.mapper")
@EnableEurekaClient
public class UrlChangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlChangeApplication.class, args);
    }

}
