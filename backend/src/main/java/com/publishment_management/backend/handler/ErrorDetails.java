package com.publishment_management.backend.handler;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails<E> {
	
	private String hostName;
	
	private String path;
	
	private LocalDateTime createdTime = LocalDateTime.now();
	
	private E message;
}
