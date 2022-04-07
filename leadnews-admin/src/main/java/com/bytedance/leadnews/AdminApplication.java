package com.bytedance.leadnews;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bytedance.leadnews.dao")
@SpringBootApplication
@Slf4j
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
        log.info("admin server 启动完成。");
    }
}
