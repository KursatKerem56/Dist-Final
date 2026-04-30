package com.publishment_management.backend.exceptions;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST("400","The record could not found"),
	INTERNAL_ERROR("500","An error occurred while processing your request"),
	INVALID_REQUEST("400","The request you sent could not resolved");
	
	private String code;
	
	private String message;
	
	MessageType(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	

}
