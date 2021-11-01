package com.lme.martianrobot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class MartianRobotsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MartianRobotsApplication.class, args);
        log.info("Application started");
    }

}
