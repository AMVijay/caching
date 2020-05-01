package com.amvijay.cache.ehcache.spring.app2.config;

import java.io.IOException;

import javax.cache.Caching;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.ehcache.xml.XmlConfiguration;
import org.ehcache.xml.exceptions.XmlConfigurationException;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class EhcacheConfiguration {

	@Bean
	public JCacheCacheManager jCacheManager() throws XmlConfigurationException, IOException {
		return new JCacheCacheManager(ehCacheManager());
	}

	@Bean(destroyMethod = "close")
	public javax.cache.CacheManager ehCacheManager() throws XmlConfigurationException, IOException {
		XmlConfiguration xmlConfiguration = new XmlConfiguration(new ClassPathResource("application-ehcache.xml").getURL());
		EhcacheCachingProvider ehcacheProvider = (EhcacheCachingProvider) Caching.getCachingProvider();
		return ehcacheProvider.getCacheManager(ehcacheProvider.getDefaultURI(), xmlConfiguration);
	}

}
