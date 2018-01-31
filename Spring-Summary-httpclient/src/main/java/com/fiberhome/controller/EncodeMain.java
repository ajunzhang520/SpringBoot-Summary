package com.fiberhome.controller;

import java.net.URLEncoder;

import com.fiberhome.httpClient.HttpUrlConnectionService;

/**
 * Description:自定义了一个Tcp/ip Monitor用于抓取如下请求，这里做了一下转发，新建一个tcp/ip monitor，当遇到请求路径为
 * http://localhost:80/VehicleRiskRating/ 就用 http://10.110.200.103:9981/VehicleRiskRating/来代理。然后可以观察request Head和
 * Response Head。
 * 
 * 该类的逻辑主要用于测试 url对特殊字符转义的效果。
 * @author sjZhang
 * @date 2018年1月30日下午3:09:21
 */
public class EncodeMain {
	public static void main(String[] args) throws Exception{
		String movementId = "mo?vem=ent%00003";
		String permitID="perm&it*00003";
		String Datetime="20180%117";
		//movementId = URLEncoder.encode(movementId,"GBK");
		//movementId = URLEncoder.encode(movementId,"utf-8");
		movementId = URLEncoder.encode(movementId,"ASCII");
		String path = "http://localhost:80/VehicleRiskRating/"+movementId+"/"+permitID+"/"+Datetime;
		Long start = System.currentTimeMillis();
		HttpUrlConnectionService httpClientUtil = new HttpUrlConnectionService();
		httpClientUtil.getDG(path);
		Long end = System.currentTimeMillis();
		System.out.println("HttpClient执行时间为" + (end - start) + "毫秒");
	}

}
