package com.microservices.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;

import java.util.concurrent.ConcurrentMap;

@SpringBootApplication
//Step-2: for enabling caching cache management support
@EnableCaching
public class C02d06SpringMicroservices17SpringBootEmployeeServiceCacheManagementEHCache {

    public static void main(String[] args) {
        SpringApplication.run(C02d06SpringMicroservices17SpringBootEmployeeServiceCacheManagementEHCache.class, args);
    }

    //Step-3: Add EH cache as a bean in Primary config class
    @Bean
    public CacheManager cacheManager(){
       net.sf.ehcache.CacheManager ch = ehCacheCacheManager().getObject();
       return new EhCacheCacheManager(ch);
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager(){
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setShared(true);
        return cmfb;
    }

    //https://stackoverflow.com/questions/12113725/how-do-i-tell-spring-cache-not-to-cache-null-value-in-cacheable-annotation

}
