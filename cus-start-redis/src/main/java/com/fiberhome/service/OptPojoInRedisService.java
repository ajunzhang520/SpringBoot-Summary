package com.fiberhome.service;

import com.fiberhome.entity.User;

/**
 * Description:定义方法
 * 
 * @author sjZhang
 * @date 2017年12月26日下午4:21:47
 */
public interface OptPojoInRedisService {
	
	User readPojo(String key);

	void writePojo(String key, User user);
}
