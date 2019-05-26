package com.example.classqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ClassQAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassQAApplication.class, args);
    }

}
