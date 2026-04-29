package com.publisment_management.backend.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.publisment_management.backend.exceptions.BaseException;
import com.publisment_management.backend.models.RootEntity;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {BaseException.class})
	public ResponseEntity<RootEntity<ApiError<?>>> handleBaseException(BaseException exception, WebRequest path) {

	    ApiError<?> apiError = createApiError(exception.getMessage(), path);

	    RootEntity<ApiError<?>> rootEntity = RootEntity.error(exception.getMessage(), apiError);

	    return ResponseEntity.badRequest().body(rootEntity);
	}
	
	private String getHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public <E> ApiError<E> createApiError(E message, WebRequest path){
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());
		
		Exception<E> exception = new Exception<>();
		exception.setCreatedTime(new Date());
		exception.setHostName(getHostname());
		exception.setPath(path.getDescription(false).substring(4));
		exception.setMessage(message);
		
		apiError.setException(exception);
		return apiError;
	}
	
}
