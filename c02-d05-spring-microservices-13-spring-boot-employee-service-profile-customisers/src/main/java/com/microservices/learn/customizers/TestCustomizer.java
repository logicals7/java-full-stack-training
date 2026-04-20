package com.microservices.learn.customizers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//Step-2: All customizers should be Spring Components
@Component
@Profile("test_profile")
//Step-1: All the customizers should implement interface EmbeddedServletContainerCustomizer
public class TestCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private static Logger logger = LoggerFactory.getLogger("TestCustomizer");
    static {
        logger.info("*****************************************");
        logger.info("Created the Test URL Customizer");
        logger.info("*****************************************");
    }

    //Step-3: Override the method customize to override the default context path and port
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setContextPath("/spring-boot-test");
        factory.setPort(8486);
    }
}
