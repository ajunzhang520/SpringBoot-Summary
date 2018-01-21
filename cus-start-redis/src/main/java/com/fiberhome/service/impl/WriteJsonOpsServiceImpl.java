package com.fiberhome.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.fiberhome.entity.User;
import com.fiberhome.service.WriteJsonOpsService;

@Service
public class WriteJsonOpsServiceImpl implements WriteJsonOpsService{


	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Override
	public void setJsonPojo(String key, User user) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){

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
	
	
	
	
	

}
