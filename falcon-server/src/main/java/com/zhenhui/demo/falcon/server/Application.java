package com.zhenhui.demo.falcon.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:server.xml", "classpath:id-gen.xml"})
@ComponentScan("com.zhenhui.demo.falcon")
@MapperScan(basePackages = "com.zhenhui.demo.falcon.service.mybatis.mapper")
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
