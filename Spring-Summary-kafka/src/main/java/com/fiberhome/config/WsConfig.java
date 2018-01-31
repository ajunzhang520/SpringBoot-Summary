package com.fiberhome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
/**
 * Description:这里有一个问题服务部署到docker中如何制定BaseAddress?
 * @author sjZhang
 * @date 2017年12月12日下午4:21:40
 */
@Configuration
public class WsConfig {
	/**
	 * 用SimpleJaxWsServiceExporter做服务导出器,把Spring管理的bean发布为JAX-WS运行时的服务端点
	 * 在浏览器访问wsdl文件地址为 http://localhost:8088/ccv3/RmsAgent?wsdl
	 * 用jws wsimport.exe生成客户端代码命令，新建一个文件夹进入cmd输入命令：
	 * wsimport -s . http://localhost:8088/ccv3/RmsAgent?wsdl
	 * @return
	 */
	@Bean
	public SimpleJaxWsServiceExporter simpleJaxWsServiceExporter(){
		SimpleJaxWsServiceExporter sJaxWs = new SimpleJaxWsServiceExporter();
		sJaxWs.setBaseAddress("http://localhost:8088/ccv3/");
		return sJaxWs;
	}

}
