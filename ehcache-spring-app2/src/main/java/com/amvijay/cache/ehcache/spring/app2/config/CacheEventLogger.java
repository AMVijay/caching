package com.amvijay.cache.ehcache.spring.app2.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
		System.out.println(cacheEvent.getKey() + " " + cacheEvent.getOldValue() + " " + cacheEvent.getNewValue());
	}
}
