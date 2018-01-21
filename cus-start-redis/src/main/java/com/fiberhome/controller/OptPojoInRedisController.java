package com.fiberhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.entity.User;
import com.fiberhome.service.OptPojoInRedisService;

/**
 * Description:写一个Pojo对象到redis中，根据key读取出来。序列化用JdkSerializationRedisSerializer处理
 * @author sjZhang
 * @date 2017年12月26日下午4:20:22
 */
@RestController
public class OptPojoInRedisController {
	
	@Autowired
	private OptPojoInRedisService opirService;
	
	/**
	 * 写一个User对象到Redis中。
	 * @param user
	 */
	@RequestMapping(value="/writePojo",method=RequestMethod.POST,consumes={"text/plain","application/*"})
	public void writePojo(@RequestBody User user){
		String key = user.getAcount();
		opirService.writePojo(key, user);
	}
	
	/**
	 * 根据key从redis里面读取User对象
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/readPojo",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	public User readPojo(@RequestParam("key") String key){
		return opirService.readPojo(key);
	}
	

}
