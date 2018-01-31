package com.fiberhome.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fiberhome.entity.in.PermitValue;
import com.fiberhome.entity.in.RequestValue;
import com.fiberhome.httpClient.HttpUrlConnectionService;

/**
 * Description:用Eclipse自带的Tcp/ip Monitor抓包测试结果，观察请求的请求头和响应头。
 * @author sjZhang
 * @date 2018年1月16日下午4:56:14
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String urlPath = "http://localhost:80/postCarRTRSToServer";
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
		HttpUrlConnectionService httpClientUtil = new HttpUrlConnectionService();
		httpClientUtil.getCarRTSVByPOST(urlPath, requestValue);

	}

}
