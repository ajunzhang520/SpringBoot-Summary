package com.fiberhome.in;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Description:输入参数
 * @author sjZhang
 * @date 2018年1月30日下午5:02:09
 */
public class RequestValue implements Serializable {
	private static final long serialVersionUID = 5079984577386220550L;
	private String requestId;
	private Date requestTime;

	public RequestValue() {
		super();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				buff.append(fields[i].getName()).append("\t=>\t").append(fields[i].get(this)).append("\n");
			} catch (IllegalAccessException ex) {
				ex.printStackTrace(System.out);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace(System.out);
			}
		}
		return buff.toString();
	}
}
