package com.fiberhome.mock;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.fiberhome.endpoint.AgentService;
import com.fiberhome.in.RequestValue;
import com.fiberhome.out.ResultValue;

@Component
@WebService(endpointInterface = "com.fiberhome.endpoint.AgentService")
public class AgentServiceImpl implements AgentService {

	@Override
	public ResultValue methodOfTest(RequestValue requestValue){

		try {
			ResultValue resultValue = new ResultValue();
			resultValue.setCode("1");
			resultValue.setMessage("SUCCESS");
			resultValue.setRequestId(requestValue.getRequestId());
			return resultValue;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
