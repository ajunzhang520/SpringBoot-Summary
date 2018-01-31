package com.fiberhome.entity.out;

import java.io.Serializable;
import java.lang.reflect.Field;

public class RmsDecision implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4632878740163390055L;
	// 驗證結果：PASS（放行）, ARREST（拘捕）, SEIZE（扣押）, SEIZE_CHECK（扣查）, SPECIFIED_CHECK（指定抽查）, RANDOM_CHECK（電腦抽查）, REPORT（通報）, REPEAT_IN（重複入境）, REPEAT_OUT（重複出境）, EXPIRED_PERMIT（文件過期）, INVALID_PERMIT（文件無效）, RESTRICTED_PERMIT（禁止過境）
	private String id;
	private String idAVC;
	private String idOLD;
	// 驗證結果的中文說明
	private String description;
	private String descriptionAVC;
	private String descriptionOLD;
	
	public RmsDecision() {
		super();
	}
	
	public RmsDecision(String id, String idAVC, String idOLD,
			String description, String descriptionAVC, String descriptionOLD) {
		super();
		this.id = id;
		this.idAVC = idAVC;
		this.idOLD = idOLD;
		this.description = description;
		this.descriptionAVC = descriptionAVC;
		this.descriptionOLD = descriptionOLD;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdAVC() {
		return idAVC;
	}
	public void setIdAVC(String idAVC) {
		this.idAVC = idAVC;
	}
	public String getIdOLD() {
		return idOLD;
	}
	public void setIdOLD(String idOLD) {
		this.idOLD = idOLD;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionAVC() {
		return descriptionAVC;
	}
	public void setDescriptionAVC(String descriptionAVC) {
		this.descriptionAVC = descriptionAVC;
	}
	public String getDescriptionOLD() {
		return descriptionOLD;
	}
	public void setDescriptionOLD(String descriptionOLD) {
		this.descriptionOLD = descriptionOLD;
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
