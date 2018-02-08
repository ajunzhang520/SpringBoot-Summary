package com.fiberhome.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:通过注解@WebListener实现监听器。
 * 
 * @author sjZhang
 * @date 2018年2月7日下午1:49:52
 */
@WebListener
public class MyListener implements ServletContextListener {
	private final Logger log = LoggerFactory.getLogger(MyListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.debug("MyListener start");
		//ServletContext context = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
