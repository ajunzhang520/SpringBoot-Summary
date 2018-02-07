package com.fiberhome.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:Servlet 3.0用注解@WebFilter声明过滤器
 * 
 * @author sjZhang
 * @date 2018年2月7日下午1:59:51
 */
@WebFilter("/user/access")
public class LogFilter implements Filter {
	private final Logger log = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**Filter可以在Servlet获得Request时修改Request，以及在Servlet给出Response之前修改Response**/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("income log filter:" + request.getRemoteHost());
		response.getWriter().write("LogFilter ADD \n");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
