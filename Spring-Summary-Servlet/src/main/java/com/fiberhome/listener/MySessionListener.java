package com.fiberhome.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:监听session的创建和销毁
 * @author sjZhang
 * @date 2018年2月7日下午2:23:48
 */
@WebListener
public class MySessionListener implements HttpSessionListener {
	private final Logger log = LoggerFactory.getLogger(MySessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		log.debug("Session"+session.getId()+"被创建了。");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		log.debug("Session"+session.getId()+"被销毁了。");
	}

}
