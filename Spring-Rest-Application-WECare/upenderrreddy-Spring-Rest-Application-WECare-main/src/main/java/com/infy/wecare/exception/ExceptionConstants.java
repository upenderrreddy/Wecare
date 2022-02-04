package com.infy.wecare.exception;

public enum ExceptionConstants {
	SERVER_ERROR("server.error"),
	USER_NOT_FOUND("user.not.found"),
	
	BOOKING_ALREADY_EXISTS("booking.already.exists"),
	BOOKING_DOESNOT_EXISTS("booking.not.found"),
	
	COACH_NOT_FOUND("coach.not.found");
	
	private final String type;
	private ExceptionConstants(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return this.type;
	}
}