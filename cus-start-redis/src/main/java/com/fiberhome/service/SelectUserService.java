package com.fiberhome.service;

import java.util.Map;

import com.fiberhome.entity.Person;
import com.fiberhome.entity.User;

public interface SelectUserService {
	//往redis里面写一个User对象
	public boolean writeUser(User user);
	
	public User getUser(String key);

	public Map<byte[], byte[]> getHashValue(String key);

	public Person loadHash(String key);

	public void writeHash(String key, Person person);
}
