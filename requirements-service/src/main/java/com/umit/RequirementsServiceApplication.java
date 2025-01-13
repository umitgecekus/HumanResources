package com.umit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RequirementsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequirementsServiceApplication.class, args);
    }

}
