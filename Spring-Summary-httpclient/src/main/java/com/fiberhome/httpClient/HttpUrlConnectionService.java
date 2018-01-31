package com.fiberhome.httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiberhome.entity.CarRealTimeScoreVO;
import com.fiberhome.entity.in.RequestValue;
import com.fiberhome.utils.JsonUtil;

/**
 * Description:用原生的HttpURLConnection来访问Http接口
 * 
 * @author sjZhang
 * @date 2018年1月12日下午3:38:58
 */
@Component
public class HttpUrlConnectionService {
	private Logger logger = LoggerFactory.getLogger(HttpUrlConnectionService.class);

	/**
	 * 对接算法接口，用GET请求。
	 * 
	 * @param path
	 * @return
	 */
	public CarRealTimeScoreVO getDG(String path) {
		String result = "";
		CarRealTimeScoreVO carRTSVO = new CarRealTimeScoreVO();
		try {
			URL restServiceURL = new URL(path);
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod("GET");// 声明请求类型
			httpConnection.setRequestProperty("Accept", "application/json");// 声明客户端接受的数据类型是json格式
			httpConnection.setRequestProperty("Accept-Charset", "UTF-8");// 声明客户端接受的数据格式是utf-8编码
			// 获取服务端响应头的信息
			if (200 != httpConnection.getResponseCode()) {
				throw new RuntimeException("failed, error code is " + httpConnection.getResponseCode());
			}
			InputStream inputStream = null;
			try {
			    inputStream = httpConnection.getInputStream();
				byte[] temp = new byte[inputStream.available()];
				if (inputStream.read(temp) != -1) {
					result = new String(temp);
				}
			} finally {
				inputStream.close();
			}
			// 对json字符串反序列化为JAVA对象，用到类ObjectMapper的方法readValue
			ObjectMapper objectMapper = new ObjectMapper();
			carRTSVO = objectMapper.readValue(result, CarRealTimeScoreVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carRTSVO;
	}

	/**
	 * 对接算法接口，算法需要CCV3传递过来的对象，多以需要用POST请求往Body中塞对象。
	 * 
	 * @param path
	 * @param requestValue
	 * @return
	 */
	public CarRealTimeScoreVO getCarRTSVByPOST(String urlPath, RequestValue requestValue) {
		// HttpClient 6.0被抛弃了
		String result = "";
		BufferedReader reader = null;

		CarRealTimeScoreVO carRTSVO = new CarRealTimeScoreVO();
		String jsonStr = "";
		try {
			jsonStr = JsonUtil.object2Json(requestValue);// 将对象序列化为json字符串
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			// 设置文件类型:
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			// 设置接收类型否则返回415错误
			// conn.setRequestProperty("accept","*/*");//此处为暴力方法设置接受所有类型，以此来防范返回415;
			conn.setRequestProperty("Accept", "application/json");

			// 往服务器里面发送数据
			if (jsonStr != null && !StringUtils.isEmpty(jsonStr)) {
				byte[] writebytes = jsonStr.getBytes();
				// 设置文件长度
				conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
				OutputStream outwritestream = conn.getOutputStream();
				outwritestream.write(jsonStr.getBytes());
				outwritestream.flush();
				outwritestream.close();
				logger.debug("hlhupload", "doJsonPost: conn" + conn.getResponseCode());
			}
			InputStream inputStream = null;
			try {
				inputStream = conn.getInputStream();
				if (conn.getResponseCode() == 200) {
					byte[] temp = new byte[inputStream.available()];
					if (conn.getInputStream().read(temp) != -1) {
						result = new String(temp);
					}
					// 对json字符串反序列化为JAVA对象，用到类ObjectMapper的方法readValue
					ObjectMapper objectMapper = new ObjectMapper();
					carRTSVO = objectMapper.readValue(result, CarRealTimeScoreVO.class);
				}
			} finally {
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return carRTSVO;
	}

}
