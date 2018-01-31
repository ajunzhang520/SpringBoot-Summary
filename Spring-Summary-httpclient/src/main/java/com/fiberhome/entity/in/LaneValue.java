package com.fiberhome.entity.in;

import java.io.Serializable;
import java.lang.reflect.Field;

// 車道
public class LaneValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8507404335761458786L;
	private String uid; // 車道識別號
	private String description; // 車道的中文名稱
	private String direction; // 方向：E（入境）, S（出境）
	private String ip; // 車道IPC的網絡地址
	private PortValue portValue; // 口岸
	
	public LaneValue() {
		super();
	}

	public LaneValue(String uid, String description, String direction,
			String ip, PortValue portValue) {
		super();
		this.uid = uid;
		this.description = description;
		this.direction = direction;
		this.ip = ip;
		this.portValue = portValue;
	}

	public LaneValue clone() {
		LaneValue o = new LaneValue();
		o.uid = this.uid;
		o.description = this.description;
		o.direction = this.direction;
		o.ip = this.ip;
		o.portValue = this.portValue==null?null:this.portValue.clone();
		return o;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LaneValue other = (LaneValue) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public PortValue getPortValue() {
		return portValue;
	}
	public void setPortValue(PortValue portValue) {
		this.portValue = portValue;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
