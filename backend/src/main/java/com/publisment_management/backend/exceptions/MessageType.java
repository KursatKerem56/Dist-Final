package com.publisment_management.backend.exceptions;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST("1001","The record could not found"),
	INVALID_REQUEST("1002","The request you sent could not resolved");
	
	private String code;
	
	private String message;
	
	MessageType(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	

}
