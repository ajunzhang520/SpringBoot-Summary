package com.fiberhome.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.fiberhome.ws.in.NotifyValue;
import com.fiberhome.ws.in.RequestValue;
import com.fiberhome.ws.out.ResultValue;

/**
 * Description:Rms代理的Web服務接口
 * @author sjZhang
 * @date 2017年12月12日下午4:22:04
 */
@Service
@WebService(serviceName="RmsAgent")//要标明serviceName
public interface IRmsAgentService {

	// movementId CCV3出入境記錄識別號。
	
	@WebMethod
	public ResultValue checkPermit(@WebParam(name="requestValue") RequestValue requestValue ) 
		throws Exception;
	/*
	 * REQUEST :
	 * requestValue.movementId != null
	 * requestValue.transactionTime != null 行車記錄時間
	 * requestValue.laneValue != null  車道
	 * requestValue.laneValue.portValue != null  
	 * requestValue.permitValue != null	 通行證
	 * requestValue.workstationIp = null
	 * requestValue.inspectorId = null 
	 * 
	 * RETUEN :
	 * resultValue.status = "SUCCESS"
	 * resultValue.rmsRiskScore != null
	 * resultValue.rmsRiskScore.rmsDecisions 包含所有引發的 Decision
	 * resultValue.rmsRiskScore.rmsWatchLists 包含所有引發的 WatchList
	 * 
	 * Sample.1
	 * resultValue.status = "SUCCESS"
	 * resultValue.rmsRiskScore != null
	 * resultValue.rmsRiskScore.rmsDecisions = (PASS,PASS,0,放行,放行,正常通過)
	 * 
	 * Sample.2
	 * resultValue.status = "SUCCESS"
	 * resultValue.rmsRiskScore != null
	 * resultValue.rmsRiskScore.rmsDecisions = (RANDOM_CHECK,RANDOM_CHECK,1,電腦抽查,電腦抽查,電腦抽查)
	 * 
	 */
	
	@WebMethod
	public ResultValue release(@WebParam(name="notifyValue") NotifyValue notifyValue )
		throws Exception;
	/*
	 * REQUEST :
	 * notifyValue.movementId != null
	 * notifyValue.transactionTime != null 開閘時間
	 * notifyValue.workstationIp != null // 車道PC網絡地址。release方法中的workstationIp值
	 * notifyValue.inspectorId != null   // 使用者編號。release方法中的inspectorId值
	 * notifyValue.rmsDecisions = null // CCV3採用的檢驗結果
	 * 
	 * RETUEN :
	 * resultValue.movementId != null
	 * resultValue.status = "SUCCESS"
	 * 
	 */

	@WebMethod
	public ResultValue verify(@WebParam(name="requestValue") NotifyValue notifyValue ) 
		throws Exception;
	/*
	 * REQUEST :
	 * notifyValue.movementId != null
	 * notifyValue.transactionTime != null 發出時間
	 * notifyValue.workstationIp = null // 車道PC網絡地址。release方法中的workstationIp值
	 * notifyValue.inspectorId = null   // 使用者編號。release方法中的inspectorId值
	 * notifyValue.rmsDecisions != null  // CCV3採用的檢驗結果
	 * 
	 * 
	 * RETUEN :
	 * resultValue.movementId != null
	 * resultValue.status = "SUCCESS"
	 * 
	 */

	
}
