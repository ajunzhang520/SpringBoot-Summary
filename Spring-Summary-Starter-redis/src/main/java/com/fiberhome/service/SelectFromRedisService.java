package com.fiberhome.service;

import java.util.List;

public interface SelectFromRedisService {

	public List<String> getAll(String key);

}
