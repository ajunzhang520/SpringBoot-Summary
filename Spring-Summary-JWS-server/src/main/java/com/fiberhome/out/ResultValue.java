package com.fiberhome.out;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Description:返回结果
 * 
 * @author sjZhang
 * @date 2018年1月30日下午5:01:58
 */
public class ResultValue implements Serializable {
	private static final long serialVersionUID = 4191413130945203529L;
	private String requestId;
	private String code;
	private String message;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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