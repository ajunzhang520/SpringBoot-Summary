package com.fiberhome.ui;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

/**
 * Description:引入Resteasy测试UI类
 * @author sjZhang
 * @date 2018年3月14日上午9:24:00
 */
@Component
@Path("/resteasy")
@Produces("application/json")
@Consumes("application/json")
public class RestResource{
	
	@GET
	@Path("/index")
	public String testRest1(){
		return "index";
	}

}
