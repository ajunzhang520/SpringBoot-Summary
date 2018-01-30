package com.fiberhome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fiberhome.dao.UserDao;
import com.fiberhome.entity.User;
import com.fiberhome.service.UserService;

@Service(value="UserService")
public class UserServiceImple implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getUser() {
		return userDao.getUser();
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public Long insert(User user) {
		try {
			int a = 1/0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return userDao.insert(user);
	}

}
