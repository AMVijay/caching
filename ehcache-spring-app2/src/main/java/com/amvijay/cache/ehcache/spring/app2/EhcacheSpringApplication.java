package com.amvijay.cache.ehcache.spring.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.amvijay.cache.ehcache.spring.app2.config.EhcacheConfiguration;

@SpringBootApplication
@Import(value = { EhcacheConfiguration.class })
public class EhcacheSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EhcacheSpringApplication.class, args);
	}
}
