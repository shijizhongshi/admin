package com.ola.qh.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CacheConfig {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	public static final String CUSTOM_CACHE_KEY_GENERATOR = "CUSTOM_CACHE_KEY_GENERATOR";
	
	@Bean
	public CacheManager cacheManager(){
		RedisCacheManager mgner = new RedisCacheManager(redisTemplate);
		mgner.setUsePrefix(true);
		return mgner;
	}
	
	@Bean(name=CacheConfig.CUSTOM_CACHE_KEY_GENERATOR)
	public KeyGenerator keygenerator(){
		return new CustomKeyGenerator();
	}
	
	public static class CustomKeyGenerator implements KeyGenerator{

		@Override
		public Object generate(Object target, Method method, Object... params) {
			String signature = target.getClass().getName()+"."+method.getName();
			signature = "zhongshi" + signature ;
			if (params.length == 0) {
				return signature;
			}
			if (params.length == 1) {
				Object param = params[0];
				if(param != null && !param.getClass().isArray()){
					return signature + param;
				}
			}
			return signature + new SimpleKey(params);
		}
	}
	
}
