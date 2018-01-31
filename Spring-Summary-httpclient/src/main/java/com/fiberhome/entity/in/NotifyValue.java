package com.fiberhome.entity.in;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fiberhome.entity.out.RmsDecision;

public class NotifyValue  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8553136786640258573L;
	private String movementId;
	private Date   transactionTime;
	
	private String workstationIp; // 車道PC網絡地址。release方法中的workstationIp值
	private String inspectorId; // 使用者編號。release方法中的inspectorId值

	private Set<RmsDecision> rmsDecisions = new HashSet<RmsDecision>(0);

	public String getMovementId() {
		return movementId;
	}

	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getWorkstationIp() {
		return workstationIp;
	}

	public void setWorkstationIp(String workstationIp) {
		this.workstationIp = workstationIp;
	}

	public String getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(String inspectorId) {
		this.inspectorId = inspectorId;
	}

	public Set<RmsDecision> getRmsDecisions() {
		return rmsDecisions;
	}

	public void setRmsDecisions(Set<RmsDecision> rmsDecisions) {
		this.rmsDecisions = rmsDecisions;
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
