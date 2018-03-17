package com.skip.challenge.model;

public class ResultModel {
	private int code;
	private String message;

	public ResultModel(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ResultModel() {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
