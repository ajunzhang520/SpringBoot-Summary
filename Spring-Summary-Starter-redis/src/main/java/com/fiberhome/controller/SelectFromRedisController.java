package com.fiberhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.service.SelectFromRedisService;

@RestController
public class SelectFromRedisController {

	@Autowired
	private SelectFromRedisService selectFromRedisService;

	@RequestMapping(value = "/getAll/{key}", method = RequestMethod.GET)
	public List<String> getAll(@PathVariable("key") String key) {
		return selectFromRedisService.getAll(key);
	}

}
