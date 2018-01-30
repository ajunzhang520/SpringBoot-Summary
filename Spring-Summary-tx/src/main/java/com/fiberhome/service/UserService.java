package com.fiberhome.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fiberhome.entity.User;

@Transactional(rollbackFor={Exception.class,RuntimeException.class})
public interface UserService {
	List<User> getUser();
	
	User getUserById(Long id);
	
	Long insert(User user);
}
