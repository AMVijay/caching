package com.amvijay.cache.redis.spring.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.amvijay.cache.redis.spring.app2.config.ApplicationCacheConfiguration;

@SpringBootApplication
@Import( value = {ApplicationCacheConfiguration.class})
public class RedisSpringApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringApp2Application.class, args);
	}

}
