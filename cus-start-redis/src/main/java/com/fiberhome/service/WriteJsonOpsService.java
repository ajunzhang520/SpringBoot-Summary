package com.fiberhome.service;

import com.fiberhome.entity.User;

public interface WriteJsonOpsService {
	
	User readPojo(String key);
	
	void setJsonPojo(String key,User user);

}
