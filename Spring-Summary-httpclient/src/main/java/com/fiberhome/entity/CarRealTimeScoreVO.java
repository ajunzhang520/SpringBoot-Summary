package com.fiberhome.entity;

import java.io.Serializable;

/**
 * @Description:对接算法车辆实时计算风险分数
 * @author zsj
 * @date 2018年1月10日 下午2:55:44
 */
public class CarRealTimeScoreVO implements Serializable {

	private static final long serialVersionUID = 7656773589017793442L;
	private String movementId;
	private Integer score;// 实时计算分数值
	private Integer isCheck;// 0表示查验，1表示不查验

	public String getMovementId() {
		return movementId;
	}

	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
}
