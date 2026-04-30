package com.publishment_management.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	
	private MessageType messageType;
	
	private String ofStatic;
	
	public String prepareErrorMessage() {
		StringBuilder builder = new StringBuilder();
		if (messageType != null) {
	        builder.append(messageType.getMessage());
	    } else {
	        builder.append("Unknown Error");
	    }
		if (ofStatic != null) {
			builder.append(" : " + ofStatic);
		}
		return builder.toString();
	}
	
}
