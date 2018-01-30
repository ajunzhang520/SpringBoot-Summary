package com.fiberhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.entity.User;
import com.fiberhome.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserUI {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/listUser")
	public List<User> listUser() throws Exception {
		return userService.getUser();
	}

	@RequestMapping("/getById/{id}")
	public User getById(@PathVariable("id") Long id) throws Exception {
		return userService.getUserById(id);
	}

	@RequestMapping("/testInsert")
	public Long testInsert() throws Exception {
		User user = new User();
		user.setName("lisi");
		user.setCreateUserId("1");
		user.setAddress("BJ");
		user.setPort("flot");
		user.setState("1");
		try {
			return userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1l;
		}
	}

}
