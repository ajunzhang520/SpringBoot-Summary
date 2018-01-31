package com.fiberhome.ws.out;

import java.io.Serializable;
import java.lang.reflect.Field;

// 驗證結果
public class ResultValue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4191413130945203529L;
	private String movementId; // CCV3出入境記錄識別號。movementId值
	private String status;
	private String statusInfo;
	
	private RmsRiskScore rmsRiskScore; //結果決定
	
	public ResultValue() {
		super();
	};

	public ResultValue(String movementId, String status, String statusInfo,
			RmsRiskScore rmsRiskScore) {
		super();
		this.movementId = movementId;
		this.status = status;
		this.statusInfo = statusInfo;
		this.rmsRiskScore = rmsRiskScore;
	}

	public String getMovementId() {
		return movementId;
	}

	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

	public RmsRiskScore getRmsRiskScore() {
		return rmsRiskScore;
	}

	public void setRmsRiskScore(RmsRiskScore rmsRiskScore) {
		this.rmsRiskScore = rmsRiskScore;
	}
	@Override
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
		{
			try	
			{
				buff.append(fields[i].getName())
				.append("\t=>\t")
				.append(fields[i].get(this))
				.append("\n");
			}
			catch (IllegalAccessException ex)
			{
				ex.printStackTrace(System.out);
			}
			catch (IllegalArgumentException ex)
			{
				ex.printStackTrace(System.out);
			}
		}
		return buff.toString();
	}		
}