package com.publishment_management.backend.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError<E> {
	private Integer status;
	
	private ErrorDetails<E> errorDetails;
}
