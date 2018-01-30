package com.fiberhome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * Description:在SpringBoot项目中显示配置Configure
 * 
 * @author sjZhang
 * @date 2017年12月26日下午4:34:09
 */
@Configuration
public class RedisConfiguration {

	@Bean(name = "jedisFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("10.110.200.17");
		connectionFactory.setPassword("6385pwd123");
		connectionFactory.setPort(6385);
		connectionFactory.setDatabase(10);
		connectionFactory.setUsePool(true);// 连接池
		return connectionFactory;
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setEnableTransactionSupport(true);// 使用redis事物
		redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());// StringRedisSerializer
		redisTemplate.setValueSerializer(redisTemplate.getDefaultSerializer());// JdkSerializationRedisSerializer
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	/**
	 * 实例化 HashOperations 对象,可以使用 Hash 类型操作
	 */
	@Bean
	public HashOperations<?, ?, ?> hashOperations(RedisTemplate<?, ?> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	/**
	 * 实例化 ValueOperations 对象,可以使用 String 操作
	 */
	@Bean
	public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	/**
	 * 实例化 ListOperations 对象,可以使用 List 操作
	 */
	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	/**
	 * 实例化 SetOperations 对象,可以使用 Set 操作
	 */
	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	/**
	 * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
	 */
	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}

}
