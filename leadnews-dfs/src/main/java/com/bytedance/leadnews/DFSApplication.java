package com.bytedance.leadnews;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DFSApplication {
    public static void main(String[] args) {
        SpringApplication.run(DFSApplication.class,args);
        log.info("DFS service 已启动！");
    }
}
