package com.fiberhome.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.entity.CarRealTimeScoreVO;
import com.fiberhome.httpClient.HttpAPIService;
import com.fiberhome.utils.JsonUtil;

/**
 * Description:用HttpClient发起请求。
 * 
 * @author sjZhang
 * @date 2018年1月16日下午8:43:00
 */
@RestController
public class HttpClientResources {
	@Autowired
	private HttpAPIService httpAPIService;

	@RequestMapping("httpclient")
	public CarRealTimeScoreVO test() throws Exception {
		String movementId = "movement%00003";
		String permitID="pe%r=m?it[00003";
		String datetime="20180%117";
		movementId = URLEncoder.encode(movementId,"GBK");
		permitID = URLEncoder.encode(permitID,"GBK");
		datetime = URLEncoder.encode(datetime,"GBK");
		String path = "http://10.110.200.103:9981/VehicleRiskRating/"+movementId+"/"+permitID+"/"+datetime;
		Long start = System.currentTimeMillis();
		String str = httpAPIService.doGet(path);
		// System.out.println(str);
		CarRealTimeScoreVO carRTSVO = new CarRealTimeScoreVO();
		// 对响应头返回的json字符串反序列化为JAVA对象
		carRTSVO = JsonUtil.jsonStr2Object(str, CarRealTimeScoreVO.class);
		Long end = System.currentTimeMillis();
		System.out.println("HttpClient执行时间为" + (end - start) + "毫秒");
		return carRTSVO;
	}

	/**
	 * 
	 * @param movementId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCarRiskScore/{movementId}/{permitID}/{Datetime}", method = RequestMethod.GET, produces = "application/json")
	public CarRealTimeScoreVO queryCarRTSVO(@PathVariable("movementId") String movementId,
			@PathVariable("permitID") String permitID, @PathVariable("Datetime") String Datetime)
					throws Exception {
		String path = "http://10.110.200.103:9981/VehicleRiskRating/"+movementId+"/"+permitID+"/"+Datetime;
		Long start = System.currentTimeMillis();
		String str = httpAPIService.doGet(path);
		// System.out.println(str);
		CarRealTimeScoreVO carRTSVO = new CarRealTimeScoreVO();
		// 对响应头返回的json字符串反序列化为JAVA对象
		carRTSVO = JsonUtil.jsonStr2Object(str, CarRealTimeScoreVO.class);
		Long end = System.currentTimeMillis();
		System.out.println("HttpClient执行时间为" + (end - start) + "毫秒");
		return carRTSVO;
	}

	/**
	 * 
	 * @param movementId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCarByHttpClient/{movementId}", method = RequestMethod.GET, produces = "application/json")
	public CarRealTimeScoreVO getCarRTSVO(@PathVariable("movementId") String movementId) throws Exception {
		String path = "http://localhost:8080/getCarRTRS/" + movementId;
		Long start = System.currentTimeMillis();
		String str = httpAPIService.doGet(path);
		// System.out.println(str);
		CarRealTimeScoreVO carRTSVO = new CarRealTimeScoreVO();
		// 对响应头返回的json字符串反序列化为JAVA对象
		carRTSVO = JsonUtil.jsonStr2Object(str, CarRealTimeScoreVO.class);
		Long end = System.currentTimeMillis();
		System.out.println("HttpClient执行时间为" + (end - start) + "毫秒");
		return carRTSVO;
	}

	/**
	 * 查询参数
	 * 
	 * @param movementId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCarScoreByGETWithQP/{movementId}", method = RequestMethod.GET, produces = "application/json")
	public CarRealTimeScoreVO testGetWithQueryParams(@PathVariable("movementId") String movementId) throws Exception {
		String path = "http://localhost:8080/getWithQueryParams";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("movementId", movementId);
		Long start = System.currentTimeMillis();
		String responseStr = httpAPIService.doGet(path, map);
		CarRealTimeScoreVO carRTSVO = new CarRealTimeScoreVO();
		carRTSVO = JsonUtil.jsonStr2Object(responseStr, CarRealTimeScoreVO.class);
		Long end = System.currentTimeMillis();
		System.out.println("HttpClient执行时间为" + (end - start) + "毫秒");
		return carRTSVO;
	}

}
