package com.fiberhome.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.entity.Person;
import com.fiberhome.entity.User;
import com.fiberhome.service.SelectUserService;

@RestController
public class SelectHashMapController {

	@Autowired
	private SelectUserService selectUserService;

	@RequestMapping(value = "/wirteHash", method = RequestMethod.POST, consumes = { "text/plain",
			"application/*" }, produces = "application/json; charset=UTF-8")
	public void wirteHash(@RequestParam("key") String key, Person person) {
		selectUserService.writeHash(key, person);
	}

	@RequestMapping(value = "/loadHash",produces = "application/json; charset=UTF-8")
	public Person loadHash(@RequestParam("key") String key) {
		return selectUserService.loadHash(key);
	}

	@RequestMapping(value = "/selectHashMap")
	public Map<byte[], byte[]> selectHashMap(@RequestParam("key") String key) {
		return selectUserService.getHashValue(key);
	}
	
	@RequestMapping(value="/getUserByRedisKey",produces = "application/json; charset=UTF-8")
	public User getUserByRedisKey(@RequestParam("key") String key){
		return selectUserService.getUser(key);
		
	}
	
	@RequestMapping(value="/writeUser",method=RequestMethod.POST,consumes={"text/plain","application/*"})
	public void writeUser(@RequestBody User user){
		selectUserService.writeUser(user);
		
	}

}
