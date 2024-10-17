package com.example.ass2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Ass2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ass2Application.class, args);
    }

}
