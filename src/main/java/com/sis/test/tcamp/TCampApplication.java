package com.sis.test.tcamp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TCampApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(TCampApplication.class, args);
            log.info("===============服务启动成功=============");
        } catch (Exception e) {
           log.error("===============服务启动失败==============");
        }
    }

}
