package com.microservices.learn.customisers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod_profile")
public class ProdCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private static Logger logger = LoggerFactory.getLogger(ProdCustomizer.class);
    static {
        logger.info("*****************************************");
        logger.info("Created the Production URL Customizer");
        logger.info("*****************************************");
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setContextPath("/spring-boot-prod");
        factory.setPort(8484);
    }
}
