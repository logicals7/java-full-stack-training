package com.microservices.learn.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    private final MessageSource messageSource;

    // Inject the existing MessageSource (avoid declaring a second messageSource bean)
    public ValidationConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Expose a Validator bean wired to the application's MessageSource so validation keys
    // (including those in ValidationMessages.properties if that bundle is registered) are resolved.
    @Bean(name = "validator")
    @Primary
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
