package com.fiberhome.entity;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -286528206779594840L;
	private String age;
	private String clazz;
	private String name;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
