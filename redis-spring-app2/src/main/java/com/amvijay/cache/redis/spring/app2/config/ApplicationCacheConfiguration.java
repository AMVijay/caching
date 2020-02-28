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
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching
public class ApplicationCacheConfiguration {

	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory()).cacheDefaults(defaultCacheConfig())
				.withInitialCacheConfigurations(Collections.singletonMap("predefined", defaultCacheConfig().disableCachingNullValues())).transactionAware().build();
		return redisCacheManager;
	}

	/**
	 * Configured this property in application.properties
	 */
	@Value("${redis.cluster.config}")
	private List<String> redisNodes;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisNodes);
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
		return jedisConnectionFactory;
	}

	@Bean
	public RedisCacheConfiguration defaultCacheConfig() {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		redisCacheConfiguration.entryTtl(Duration.ofSeconds(1));
		redisCacheConfiguration.disableCachingNullValues();
		return redisCacheConfiguration;
	}

}
