package com.fiberhome.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Description:自定义HealthIndicator
 * 
 * @author sjZhang
 * @date 2018年2月22日下午8:30:18
 */
@Component
public class SelfHealthIndicator implements HealthIndicator {
	private Logger log = LoggerFactory.getLogger(SelfHealthIndicator.class);

	@Override
	public Health health() {
		// TODO Auto-generated method stub
		log.debug("进入SelfHealthIndicator中");
		return Health.up().build();
	}

}
