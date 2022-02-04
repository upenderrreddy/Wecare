package com.infy.wecare.dto;

public class ErrorMessage {
	public ErrorMessage(String message) {
		super();
		this.message = message;
	}

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
