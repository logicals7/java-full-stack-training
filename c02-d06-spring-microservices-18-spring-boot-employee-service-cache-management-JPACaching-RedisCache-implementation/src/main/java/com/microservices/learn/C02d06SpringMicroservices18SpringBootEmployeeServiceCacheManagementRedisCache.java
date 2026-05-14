package com.microservices.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
//Step-2: for enabling caching cache management support
@EnableCaching
public class C02d06SpringMicroservices18SpringBootEmployeeServiceCacheManagementRedisCache {

    public static void main(String[] args) {
        SpringApplication.run(C02d06SpringMicroservices18SpringBootEmployeeServiceCacheManagementRedisCache.class, args);
    }

    //https://stackoverflow.com/questions/12113725/how-do-i-tell-spring-cache-not-to-cache-null-value-in-cacheable-annotation

}
