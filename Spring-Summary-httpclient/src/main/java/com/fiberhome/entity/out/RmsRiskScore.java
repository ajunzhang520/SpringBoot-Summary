package com.fiberhome.entity.out;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class RmsRiskScore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3264942851024148294L;
	
	private Set<Integer> scores = new HashSet<Integer>(0);	//風險評估得分集合
	
	private Set<RmsDecision> rmsDecisions = new HashSet<RmsDecision>(0);	//驗證結果集合
	private Set<RmsWatchList> rmsWatchLists = new HashSet<RmsWatchList>(0);		//監察名單集合
	
	public RmsRiskScore() {
		super();
	}

	public Set<Integer> getScores() {
		return scores;
	}

	public void setScores(Set<Integer> scores) {
		this.scores = scores;
	}

	public Set<RmsDecision> getRmsDecisions() {
		return rmsDecisions;
	}

	public void setRmsDecisions(Set<RmsDecision> rmsDecisions) {
		this.rmsDecisions = rmsDecisions;
	}

	public Set<RmsWatchList> getRmsWatchLists() {
		return rmsWatchLists;
	}

	public void setRmsWatchLists(Set<RmsWatchList> rmsWatchLists) {
		this.rmsWatchLists = rmsWatchLists;
	}

	public void addScore(Integer score) {
		this.scores.add(score);
	}
	
	public void addRmsDecision(RmsDecision rmsDecision) {		
		this.rmsDecisions.add(rmsDecision);		
	}
	
	public void addRmsWatchList(RmsWatchList rmsWatchList) {
		this.rmsWatchLists.add(rmsWatchList);
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
