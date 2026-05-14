package com.microservices.learn.c02d06springmicroservices19springbootemployeeserviceactuators;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class C02D06SpringMicroservices19SpringBootEmployeeServiceActuatorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(C02D06SpringMicroservices19SpringBootEmployeeServiceActuatorsApplication.class, args);
        System.out.println("Actuator Demo app is running...!!");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
