package com.microservices.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.util.concurrent.ConcurrentMap;

@SpringBootApplication
//Step-3: for enabling caching cache management support
@EnableCaching
public class C02d06SpringMicroservices15SpringBootEmployeeServiceCacheManagementClearCache {


    public static void main(String[] args) {
        SpringApplication.run(C02d06SpringMicroservices15SpringBootEmployeeServiceCacheManagementClearCache.class, args);
    }

    //Step-2: Cache Manager implementation
    @Bean
    public CacheManager cacheManager(){
        ConcurrentMapCacheManager cache = new ConcurrentMapCacheManager("empCacheSpace");
        //parameter empCacheSpace is the name of the cache
        //in prod env cache manager that is used is: GuavaCache, EHCache, RedisCache
        return cache;
    }

    //https://stackoverflow.com/questions/12113725/how-do-i-tell-spring-cache-not-to-cache-null-value-in-cacheable-annotation

}
