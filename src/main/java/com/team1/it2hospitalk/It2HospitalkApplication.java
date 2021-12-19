package com.team1.it2hospitalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class It2HospitalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(It2HospitalkApplication.class, args);
    }

}