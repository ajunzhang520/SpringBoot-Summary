/**
 * Copyright &copy; <a href="http://www.fiberhome.com">烽火</a> All rights reserved.
 */

package com.fiberhome.entity;

import java.io.Serializable;

/**
 * 用户.
 * 
 * @author chenb
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1757475622243875292L;
	private Long id;
	private String acount;
	private String email;
	private String firstname;// 存储了澳门海关HR系统的中文姓名
	private String lastname;// 存储了澳门海关HR系统的葡文姓名
	private short status;
	private String addr1;
	private String addr2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String phone;
	private String languageCode;
	private String orginfo;
	protected String mobile;
	protected String fax;
	protected String address;
	protected String photo;
	protected String education;
	protected Short title;

	/**
	 * 对接澳门海关原有的系统扩展字段
	 */
	private String categoria;// 職級
	private String categoriaName;// 職稱
	private String sid;// HRM STAFF_ID
	private String saNo;// 海關人員編號
	private String abbreviation;// 職稱簡稱
	private String titleDescription;// 主管職位

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getOrginfo() {
		return orginfo;
	}

	public void setOrginfo(String orginfo) {
		this.orginfo = orginfo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Short getTitle() {
		return title;
	}

	public void setTitle(Short title) {
		this.title = title;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoriaName() {
		return categoriaName;
	}

	public void setCategoriaName(String categoriaName) {
		this.categoriaName = categoriaName;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSaNo() {
		return saNo;
	}

	public void setSaNo(String saNo) {
		this.saNo = saNo;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getTitleDescription() {
		return titleDescription;
	}

	public void setTitleDescription(String titleDescription) {
		this.titleDescription = titleDescription;
	}
}
