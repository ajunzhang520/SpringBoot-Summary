package com.fiberhome.ws.impl;

import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fiberhome.kafka.MessageProducer;
import com.fiberhome.utils.Lock;
import com.fiberhome.utils.LockHolder;
import com.fiberhome.utils.Param;
import com.fiberhome.ws.endpoint.IRmsAgentService;
import com.fiberhome.ws.in.NotifyValue;
import com.fiberhome.ws.in.RequestValue;
import com.fiberhome.ws.out.ResultValue;
import com.fiberhome.ws.out.RmsRiskScore;

/**
 * Description:Rms的实现类，处理返回结果逻辑。
 * @author sjZhang
 * @date 2017年12月15日下午3:12:32
 */
@Component
@WebService(endpointInterface = "com.fiberhome.ws.endpoint.IRmsAgentService")
public class RmsAgentServiceImpl implements IRmsAgentService {
	private Logger logger = LoggerFactory.getLogger(RmsAgentServiceImpl.class);

	@Autowired
	private Environment env;

	@Override
	@WebMethod
	public ResultValue checkPermit(@WebParam(name = "requestValue") RequestValue requestValue) throws Exception {
		// 声明返回结果
		ResultValue resultValue = new ResultValue();
		RmsRiskScore rmsRiskScore = new RmsRiskScore();

		// 发送消息给kafka
		MessageProducer messageProducer = new MessageProducer();
		String checkPermitKey = UUID.randomUUID().toString().replaceAll("-", "");// 给每一个消息生成一个uuid作为唯一识别符号
		String checkPermitValue = requestValue.getPermitValue().getId();// 需要和算法沟通，算法需要什么数据。拼接String。
		System.out.println("checkPermitValue:"+checkPermitValue);
		messageProducer.sendMsg("checkPermitValue", checkPermitKey, checkPermitValue);// 发送该消息到kafka

		Param.isSend = true;//已经发送
		
		//异步转同步，发送消息给kafka是一个异步的操作，发送完之后流程就断了。这是我们要给执行这个操作的线程上锁，让其阻塞。
		//当kafka消费线程监听到对应的这个消息消费了就唤醒该线程，继续执行逻辑
		Lock lock = new Lock();
		LockHolder.addLock(checkPermitKey, lock);
		Object resObj = new Object();
		// 获取配置文件定义的lock超时时间
		long timeout = Long.parseLong(env.getProperty("cus.middleware.ws.lock.timeout"));
		synchronized (lock) {
			try {
				if (!lock.isNotified()) {
					lock.wait(timeout);
				}
			} catch (InterruptedException e) {
				// 超时之后的逻辑
				e.printStackTrace();
				resultValue.setStatus("FAILED");
				rmsRiskScore.addScore(10);
				resultValue.setRmsRiskScore(rmsRiskScore);
				logger.debug("kafka消息发送超时");
				return resultValue;
			}

			resObj = lock.getMessage();
			LockHolder.removeLock(checkPermitKey);
		}
		// 根据resObj的内容生成返回结果(逻辑未实现)
		logger.info("解锁执行返回逻辑");
		logger.info(resObj.toString());
		resultValue.setStatus("SUCCESS");
		rmsRiskScore.addScore(Integer.parseInt(resObj.toString()));
		resultValue.setRmsRiskScore(rmsRiskScore);
		return resultValue;
	}

	@Override
	@WebMethod
	public ResultValue release(@WebParam(name = "notifyValue") NotifyValue notifyValue) throws Exception {
		// TODO Auto-generated method stub
		ResultValue resultValue = new ResultValue();
		resultValue.setStatus("SUCCESS");

		return resultValue;
	}

	@Override
	@WebMethod
	public ResultValue verify(@WebParam(name = "requestValue") NotifyValue notifyValue) throws Exception {
		// TODO Auto-generated method stub
		ResultValue resultValue = new ResultValue();
		resultValue.setStatus("SUCCESS");

		return resultValue;
	}

}
