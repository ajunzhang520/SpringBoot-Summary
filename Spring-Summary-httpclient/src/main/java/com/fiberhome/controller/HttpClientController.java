package com.fiberhome.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.entity.CarRealTimeScoreVO;
import com.fiberhome.entity.in.PermitValue;
import com.fiberhome.entity.in.RequestValue;
import com.fiberhome.httpClient.HttpUrlConnectionService;

/**
 * Description:用HttpUrlConnection发送请求。模拟客户端，要接受POST请求了。
 * @author sjZhang
 * @date 2018年1月15日下午2:32:25
 */
@RestController
public class HttpClientController {
	
	@Autowired
	private HttpUrlConnectionService httpUrlConnectionService;
	
	@RequestMapping(value="/getCarRTSVO/{movementId}",method=RequestMethod.GET,produces="application/json")
	public CarRealTimeScoreVO getCarRTSVO(@PathVariable("movementId") String movementId){
		String path="http://localhost:8080/getCarRTRS/"+movementId;
		Long start = System.currentTimeMillis();
		CarRealTimeScoreVO carRTSVO = new CarRealTimeScoreVO();
		carRTSVO=httpUrlConnectionService.getDG(path);
		Long end = System.currentTimeMillis();
		System.out.println("HttpUrlConnection执行时间为"+(end-start)+"毫秒");
		return carRTSVO;
	}
	
	@RequestMapping(value="/getCarRTSVOByPOST",method=RequestMethod.GET,produces="application/json")
	public CarRealTimeScoreVO getCarRTSVOByPOST(){
		String urlPath = "http://localhost:8080/postCarRTRSToServer";
		RequestValue requestValue = new RequestValue();
		requestValue.setMovementId("1212123fdf22");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String dstr="2018-01-16"; 
		java.util.Date date=null;
		try {
			date=sdf.parse(dstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		requestValue.setTransactionTime(date);
		PermitValue permitValue=new PermitValue();
		permitValue.setId("FFFDDDSDFD###");
		permitValue.setPermitNo("adfadf");
		requestValue.setPermitValue(permitValue);
		return httpUrlConnectionService.getCarRTSVByPOST(urlPath, requestValue);
	}
	
	@RequestMapping("test")
	public String test(){
		return "Test";
	}

}
