package com.fiberhome.service.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.fiberhome.entity.Person;
import com.fiberhome.entity.User;
import com.fiberhome.service.SelectUserService;

@Service
public class SelectUserServiceImple implements SelectUserService {

	private Logger logger = LoggerFactory.getLogger(SelectUserServiceImple.class);

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Autowired
	HashOperations<String, byte[], byte[]> hashOperations;

	HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

	@Override
	public void writeHash(String key, Person person) {
		Map<byte[], byte[]> mappedHash = mapper.toHash(person);
		hashOperations.putAll(key, mappedHash);
	}

	@Override
	public Person loadHash(String key) {
		Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
		return (Person) mapper.fromHash(loadedHash);
	}

	@Override
	public Map<byte[], byte[]> getHashValue(String key) {
		// Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
		// System.out.println(map);

		Map<byte[], byte[]> loadHash = hashOperations.entries(key);
		System.out.println(loadHash);
		return loadHash;
	}

	// 往redis里面写一个User对象
	@Override
	public boolean writeUser(User user) {
		 boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				 ValueOperations<String, Serializable> valueOper = redisTemplate.opsForValue();
                 valueOper.set("acount1", user);
                 return true;
			}
		});
		return result;
	}
	
	//查询一个对象出来,RedisTemplate存储pojo的时候要序列化存储，所以读取的时候要反序列化。
	@Override
	public User getUser(String key) {
		User user = redisTemplate.execute(new RedisCallback<User>() {
			@Override
			public User doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> keyserializer = redisTemplate.getStringSerializer();
				byte[] value = connection.get(keyserializer.serialize(key));
				RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
				return (User) valueSerializer.deserialize(value);
			}
		});
		return user;
	}
	
}
