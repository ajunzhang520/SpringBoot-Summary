package com.fiberhome.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import com.fiberhome.mock.AgentService;

@Configuration
public class RmsConfig {
//	@Bean
//	public Jaxb2Marshaller marshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		marshaller.setContextPath("com.fiberhome.endpoint");
//		return marshaller;
//	}
//
//	@Bean
//	public RmsClient wsClient(Jaxb2Marshaller marshaller) {
//		RmsClient client = new RmsClient();
//		client.setDefaultUri("http://localhost:8088/ccv3/RmsAgent?wsdl");
//		client.setMarshaller(marshaller);
//		client.setUnmarshaller(marshaller);
//		return client;
//	}
	
	@Bean
	public JaxWsPortProxyFactoryBean rmsClient(){
		JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
		try {
			proxy.setWsdlDocumentUrl(new URL("http://localhost:8088/agent/RmsAgent?wsdl"));
			proxy.setServiceName("AgentServiceImplService");
			proxy.setPortName("AgentServiceImplPort");
			proxy.setServiceInterface(AgentService.class);
			proxy.setNamespaceUri("http://mock.fiberhome.com/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxy;
		
	}

}
