package com.fiberhome.entity.in;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class PermitValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1752672222473281204L;
	
	private String id; // 通行證編號
	private String permitNo; // 通行證短編號
	private Integer permitYear; // 通行證登記年份
	private Integer permitVersion; // 通行證發出次數
	private String vehicleClass; // 車輛級別
	private String macaoLicensePlateNo; // 車牌編號
	private String hongkongLicensePlateNo; // 香港車牌編號
	private String chinaLicensePlateNo; // 國內車牌編號
	private String chinaLicensePlateNo2; // 國內車牌編號
	private String brand; // 車輛品牌
	private String color; // 車身顏色
	private String model; // 車輛型號
	private String vehicleType; // 車輛種類
	private String ownerChineseName; // 車主中文名稱
	private String ownerPortugueseName; // 車主葡文名稱
	private String ownerIdCardType;
	private String ownerIdCard;
	private String ownerFsaNo; 	
	private String dr1ChineseName; // 駕駛員
	private String dr1PortugueseName;
	private String dr1IdCardType;
	private String dr1IdCard;
	private String dr1FsaNo;	
	private String dr2ChineseName;
	private String dr2PortugueseName;
	private String dr2Address;
	private String dr2IdCardType;
	private String dr2IdCard;
	private String dr2FsaNo;
	private String dr3ChineseName;
	private String dr3PortugueseName;
	private String dr3IdCardType;
	private String dr3IdCard;
	private String dr3FsaNo;
	private String approvedBy; // 通行證批核人
	private String remark; // 備註
	private Date   issueDate; // 發證日期
	private String vehicleClassDescription; // 車輛級別的說明
	private String vehicleTypeDescription; // 車輛種類的說明
	private String managementMethods; // 管理辦法
	private String ports; // 可通行口岸	
	private Date   expiryDate; // 相關口岸有效期
	
	public PermitValue() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public Integer getPermitYear() {
		return permitYear;
	}

	public void setPermitYear(Integer permitYear) {
		this.permitYear = permitYear;
	}

	public Integer getPermitVersion() {
		return permitVersion;
	}

	public void setPermitVersion(Integer permitVersion) {
		this.permitVersion = permitVersion;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getMacaoLicensePlateNo() {
		return macaoLicensePlateNo;
	}

	public void setMacaoLicensePlateNo(String macaoLicensePlateNo) {
		this.macaoLicensePlateNo = macaoLicensePlateNo;
	}

	public String getChinaLicensePlateNo() {
		return chinaLicensePlateNo;
	}

	public void setChinaLicensePlateNo(String chinaLicensePlateNo) {
		this.chinaLicensePlateNo = chinaLicensePlateNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getOwnerChineseName() {
		return ownerChineseName;
	}

	public void setOwnerChineseName(String ownerChineseName) {
		this.ownerChineseName = ownerChineseName;
	}

	public String getOwnerPortugueseName() {
		return ownerPortugueseName;
	}

	public void setOwnerPortugueseName(String ownerPortugueseName) {
		this.ownerPortugueseName = ownerPortugueseName;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getVehicleClassDescription() {
		return vehicleClassDescription;
	}

	public void setVehicleClassDescription(String vehicleClassDescription) {
		this.vehicleClassDescription = vehicleClassDescription;
	}

	public String getVehicleTypeDescription() {
		return vehicleTypeDescription;
	}

	public void setVehicleTypeDescription(String vehicleTypeDescription) {
		this.vehicleTypeDescription = vehicleTypeDescription;
	}

	public String getManagementMethods() {
		return managementMethods;
	}

	public void setManagementMethods(String managementMethods) {
		this.managementMethods = managementMethods;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getHongkongLicensePlateNo() {
		return hongkongLicensePlateNo;
	}

	public void setHongkongLicensePlateNo(String hongkongLicensePlateNo) {
		this.hongkongLicensePlateNo = hongkongLicensePlateNo;
	}

	public String getChinaLicensePlateNo2() {
		return chinaLicensePlateNo2;
	}

	public void setChinaLicensePlateNo2(String chinaLicensePlateNo2) {
		this.chinaLicensePlateNo2 = chinaLicensePlateNo2;
	}

	public String getOwnerIdCardType() {
		return ownerIdCardType;
	}

	public void setOwnerIdCardType(String ownerIdCardType) {
		this.ownerIdCardType = ownerIdCardType;
	}

	public String getOwnerIdCard() {
		return ownerIdCard;
	}

	public void setOwnerIdCard(String ownerIdCard) {
		this.ownerIdCard = ownerIdCard;
	}

	public String getOwnerFsaNo() {
		return ownerFsaNo;
	}

	public void setOwnerFsaNo(String ownerFsaNo) {
		this.ownerFsaNo = ownerFsaNo;
	}

	public String getDr1ChineseName() {
		return dr1ChineseName;
	}

	public void setDr1ChineseName(String dr1ChineseName) {
		this.dr1ChineseName = dr1ChineseName;
	}

	public String getDr1PortugueseName() {
		return dr1PortugueseName;
	}

	public void setDr1PortugueseName(String dr1PortugueseName) {
		this.dr1PortugueseName = dr1PortugueseName;
	}

	public String getDr1IdCardType() {
		return dr1IdCardType;
	}

	public void setDr1IdCardType(String dr1IdCardType) {
		this.dr1IdCardType = dr1IdCardType;
	}

	public String getDr1IdCard() {
		return dr1IdCard;
	}

	public void setDr1IdCard(String dr1IdCard) {
		this.dr1IdCard = dr1IdCard;
	}

	public String getDr1FsaNo() {
		return dr1FsaNo;
	}

	public void setDr1FsaNo(String dr1FsaNo) {
		this.dr1FsaNo = dr1FsaNo;
	}

	public String getDr2ChineseName() {
		return dr2ChineseName;
	}

	public void setDr2ChineseName(String dr2ChineseName) {
		this.dr2ChineseName = dr2ChineseName;
	}

	public String getDr2PortugueseName() {
		return dr2PortugueseName;
	}

	public void setDr2PortugueseName(String dr2PortugueseName) {
		this.dr2PortugueseName = dr2PortugueseName;
	}

	public String getDr2Address() {
		return dr2Address;
	}

	public void setDr2Address(String dr2Address) {
		this.dr2Address = dr2Address;
	}

	public String getDr2IdCardType() {
		return dr2IdCardType;
	}

	public void setDr2IdCardType(String dr2IdCardType) {
		this.dr2IdCardType = dr2IdCardType;
	}

	public String getDr2IdCard() {
		return dr2IdCard;
	}

	public void setDr2IdCard(String dr2IdCard) {
		this.dr2IdCard = dr2IdCard;
	}

	public String getDr2FsaNo() {
		return dr2FsaNo;
	}

	public void setDr2FsaNo(String dr2FsaNo) {
		this.dr2FsaNo = dr2FsaNo;
	}

	public String getDr3ChineseName() {
		return dr3ChineseName;
	}

	public void setDr3ChineseName(String dr3ChineseName) {
		this.dr3ChineseName = dr3ChineseName;
	}

	public String getDr3PortugueseName() {
		return dr3PortugueseName;
	}

	public void setDr3PortugueseName(String dr3PortugueseName) {
		this.dr3PortugueseName = dr3PortugueseName;
	}

	public String getDr3IdCardType() {
		return dr3IdCardType;
	}

	public void setDr3IdCardType(String dr3IdCardType) {
		this.dr3IdCardType = dr3IdCardType;
	}

	public String getDr3IdCard() {
		return dr3IdCard;
	}

	public void setDr3IdCard(String dr3IdCard) {
		this.dr3IdCard = dr3IdCard;
	}

	public String getDr3FsaNo() {
		return dr3FsaNo;
	}

	public void setDr3FsaNo(String dr3FsaNo) {
		this.dr3FsaNo = dr3FsaNo;
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

