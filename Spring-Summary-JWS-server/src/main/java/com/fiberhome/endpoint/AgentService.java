package com.fiberhome.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.fiberhome.in.RequestValue;
import com.fiberhome.out.ResultValue;

@Service
@WebService(serviceName = "RmsAgent") // 要标明serviceName
public interface AgentService {
	@WebMethod
	public ResultValue methodOfTest(@WebParam(name = "requestValue") RequestValue requestValue) throws Exception;
}
