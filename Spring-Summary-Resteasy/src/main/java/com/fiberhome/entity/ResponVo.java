package com.fiberhome.entity;

import java.io.Serializable;

public class ResponVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResponVo fail() {
		ResponVo responVo = new ResponVo();
		responVo.setCode("2");
		responVo.setMessage("fail");
		return responVo;
	}

	public static ResponVo success() {
		ResponVo responVo = new ResponVo();
		responVo.setCode("1");
		responVo.setMessage("success");
		return responVo;
	}

	public ResponVo add(Object data) {
		this.setData(data);
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponVo() {
	}

	public ResponVo(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
