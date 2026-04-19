package com.microservices.learn.customisers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test_profile")
public class TestCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private static Logger logger = LoggerFactory.getLogger(TestCustomizer.class);
    static {
        logger.info("*****************************************");
        logger.info("Created the Test URL Customizer");
        logger.info("*****************************************");
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setContextPath("/spring-boot-test");
        factory.setPort(8486);
    }
}
