package com.fiberhome.config;

import java.util.concurrent.Executor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fiberhome.thread.ReceiveMessageService;

/**
 * Description:通过继承ApplicationContextAware类使SpringBoot服务在启动的时候就去启动线程ReceiveMessageService
 * @author sjZhang
 * @date 2017年12月15日下午2:50:34
 */
@Component
public class StartUpListenerKafkaConsumer implements ApplicationContextAware{
	
	@Autowired
	private ReceiveMessageService receiveMessageService;
	
	@Autowired
	private Executor executor;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		executor.execute(receiveMessageService);
	}

}
