package com.amvijay.cache.redis.spring.app2.config;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class ApplicationCacheConfiguration {

	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = RedisCacheManager
				.builder(standaloneConnectionFactory())
				.cacheDefaults(defaultCacheConfig())
				.withInitialCacheConfigurations(Collections.singletonMap("predefined", defaultCacheConfig().disableCachingNullValues()))
				.transactionAware()
				.build();
		return redisCacheManager;
	}

	/**
	 * Configured this property in application.properties
	 */
	@Value("${redis.cluster.config}")
	private List<String> redisNodes;
	
	@Value("${redis.password}")
	private String redisPassword;

	@Value("${redis.standalone.server}")
	private String redisHost;

	@Value("${redis.standalone.port}")
	private String redisPort;

//	@Bean
//	public JedisConnectionFactory clusterConnectionFactory() {
//		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisNodes);
//		redisClusterConfiguration.setPassword(redisPassword);
//		
//		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//	    jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
//	    jedisClientConfiguration.useSsl();
//	    jedisClientConfiguration.usePooling();
//		
//		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration,jedisClientConfiguration.build());
//		return jedisConnectionFactory;
//	}

	@Bean
	public JedisConnectionFactory standaloneConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, Integer.parseInt(redisPort));
		if(!redisPassword.isEmpty()) {
			redisStandaloneConfiguration.setPassword(redisPassword);
		}
		
		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
	    jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
	    jedisClientConfiguration.useSsl();
	    jedisClientConfiguration.usePooling();
		
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
		return jedisConnectionFactory;
	}

	@Bean
	public RedisCacheConfiguration defaultCacheConfig() {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		redisCacheConfiguration.entryTtl(Duration.ofSeconds(1000));
		redisCacheConfiguration.disableCachingNullValues();
		return redisCacheConfiguration;
	}
	
	@Bean
	public RedisTemplate<String,Object> redisTemplate() {
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
		redisTemplate.setConnectionFactory(standaloneConnectionFactory());
		redisTemplate.setKeySerializer( new StringRedisSerializer() );
		return redisTemplate;
	}

}
