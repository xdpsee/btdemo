package com.zhenhui.demo.falcon.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:server.xml", "classpath:id-gen.xml"})
@ComponentScan("com.zhenhui.demo.falcon")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
