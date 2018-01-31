package com.fiberhome.entity.in;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class RequestValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5079984577386220550L;
	private String movementId;
	private Date   transactionTime;
	
	private PermitValue permitValue; // 通行證
	private LaneValue   laneValue; // 車道
	
	public RequestValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RequestValue(String movementId, Date transactionTime, 
			PermitValue permitValue, LaneValue laneValue) {
		super();
		this.movementId = movementId;
		this.transactionTime = transactionTime;
		this.permitValue = permitValue;
		this.laneValue = laneValue;		
	}
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
	public PermitValue getPermitValue() {
		return permitValue;
	}
	public void setPermitValue(PermitValue permitValue) {
		this.permitValue = permitValue;
	}
	public LaneValue getLaneValue() {
		return laneValue;
	}
	public void setLaneValue(LaneValue laneValue) {
		this.laneValue = laneValue;
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
