package com.araa.ncd.model;

import java.io.Serializable;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int score;
	private String screening;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getScreening() {
		return screening;
	}
	public void setScreening(String screening) {
		this.screening = screening;
	}
	
	
}
