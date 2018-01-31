package com.fiberhome.entity.in;

import java.io.Serializable;
import java.lang.reflect.Field;

// 口岸
public class PortValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7674143511812518013L;
	private String uid; // 口岸識別號
	private String description; // 口岸的中文名稱
	
	public PortValue() {}

	public PortValue(String uid, String description) {
		super();
		this.uid = uid;
		this.description = description;
	}

	public PortValue clone() {
		PortValue p = new PortValue();
		p.uid = this.uid;
		p.description = this.description;
		return p;
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
		PortValue other = (PortValue) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUid() {
		return uid;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
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
