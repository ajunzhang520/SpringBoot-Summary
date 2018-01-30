package com.fiberhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.endpoint.RequestValue;
import com.fiberhome.endpoint.ResultValue;
import com.fiberhome.mock.AgentService;
import com.fiberhome.mock.Exception;

@RestController
public class RmsController {

	@Autowired
	private AgentService service;

	@RequestMapping(value = "/test")
	public Object test() {
		RequestValue requestValue = new RequestValue();
		requestValue.setRequestId("aaa");
		ResultValue value=new ResultValue();
		try {
			value = service.methodOfTest(requestValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
