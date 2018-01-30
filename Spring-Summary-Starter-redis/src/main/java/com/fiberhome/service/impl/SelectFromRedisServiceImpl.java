package com.fiberhome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.fiberhome.entity.User;
import com.fiberhome.service.SelectFromRedisService;

/**
 * Description:key=rtest
 * 
 * @author sjZhang
 * @date 2017年12月25日上午11:06:12
 */
@Service
public class SelectFromRedisServiceImpl implements SelectFromRedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public List<String> getAll(String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));
				return serializer.deserialize(value);
			}

		});

		List<String> resultList = new ArrayList<String>();
		resultList.add(result);
		return resultList;
	}

}
