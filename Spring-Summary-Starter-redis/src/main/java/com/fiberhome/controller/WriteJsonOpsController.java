package com.fiberhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.entity.User;
import com.fiberhome.service.WriteJsonOpsService;

@RestController
public class WriteJsonOpsController {
	
	@Autowired
	private WriteJsonOpsService  writeJsonOpsService;
	
	
	@RequestMapping(value="/setJsonOps",method=RequestMethod.POST,consumes={"text/plain","application/*"})
	public void setJsonOps(@RequestBody User user){
		writeJsonOpsService.setJsonPojo(user.getAcount(), user);
	} 
	
	@RequestMapping(value="/readPojo",produces = "application/json; charset=UTF-8")
	public User readPojo(@RequestParam("key") String key){
		return writeJsonOpsService.readPojo(key);
		
	}
	
	

}
