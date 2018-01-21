package com.fiberhome.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.fiberhome.entity.User;

/**
 * Description:写一个Pojo对象到redis中，根据key读取出来。序列化用JdkSerializationRedisSerializer处理
 * 
 * @author sjZhang
 * @date 2017年12月26日下午4:24:54
 */
@Service
public class OptPojoInRedisServiceImpl implements com.fiberhome.service.OptPojoInRedisService {

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Override
	public User readPojo(String key) {
		User user = redisTemplate.execute(new RedisCallback<User>() {
			@Override
			public User doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> keySer = redisTemplate.getStringSerializer();
				byte[] keyByte = keySer.serialize(key);

				RedisSerializer<User> valueSer = (RedisSerializer<User>) redisTemplate.getDefaultSerializer();
				byte[] valueByte = connection.get(keyByte);

				return valueSer.deserialize(valueByte);
			}
		});
		return user;
	}

	@Override
	public void writePojo(String key, User user) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> keySer = redisTemplate.getStringSerializer();
				RedisSerializer<User> valueSer = (RedisSerializer<User>) redisTemplate.getDefaultSerializer();
				byte[] value = valueSer.serialize(user);
				connection.set(keySer.serialize(key), value);
				return true;
			}
		});
	}

}
