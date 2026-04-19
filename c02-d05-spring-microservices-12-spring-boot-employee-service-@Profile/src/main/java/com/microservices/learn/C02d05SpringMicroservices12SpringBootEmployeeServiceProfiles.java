package com.microservices.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class C02d05SpringMicroservices12SpringBootEmployeeServiceProfiles {

    /*
    Method-4: Main Class
    Comment the line: SpringApplication.run(C02d05SpringMicroservices12SpringBootEmployeeServiceProfiles.class, args);
    We will use the same SpringApplication.run method. But before run, we will create an environment & we will set the profile in that environment & then run the app.
    For that we will use certain classes/interface: ConfigurableEnvironment
     */
    public static void main(String[] args) {
        //SpringApplication.run(C02d05SpringMicroservices12SpringBootEmployeeServiceProfiles.class, args);
        //Create env & set profile
        ConfigurableEnvironment env = new StandardEnvironment();
        env.setActiveProfiles("Logging_Profile");

        //Create spring app - set env & run
        SpringApplication app = new SpringApplication(C02d05SpringMicroservices12SpringBootEmployeeServiceProfiles.class);
        app.setEnvironment(env);
        app.run();
    }

}
