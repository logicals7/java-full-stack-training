package com.microservices.learn.customizers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


/*
ProdCustomizer is a component class which will get loaded when prod_profile is active.
- This class implements the WebServerFactoryCustomizer which is taking ConfigurableServletWebServerFactory context.
- When you implement this interface it used customize method to override.
- Using this method and the ConfigurableServletWebServerFactory context, we can set the context path which is /spring-boot-prod.

-What is context path?
-- It will change the url path as:
-- http://localhost:8081/spring-boot-prod/emp/controller/addEmp

- Using this method we also set the port number.
 */

//Step-2: All customizers should be Spring Components
@Component
@Profile("prod_profile")
//Step-1: All the customizers should implement interface EmbeddedServletContainerCustomizer
public class ProdCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private static Logger logger = LoggerFactory.getLogger("ProdCustomizer");
    static {
        logger.info("*****************************************");
        logger.info("Created the Production URL Customizer");
        logger.info("*****************************************");
    }

    //Step-3: Override the method customize to override the default context path and port

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setContextPath("/spring-boot-prod");
        factory.setPort(8484);
    }
}

//https://www.baeldung.com/embeddedservletcontainercustomizer-configurableembeddedservletcontainer-spring-boot
