package com.microservices.learn.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class Internationalization {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
        bundle.setBasenames("messages");//file name
        return bundle;
    }

    @Bean //Accept-language=fr
    public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

}
