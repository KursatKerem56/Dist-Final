package com.publishment_management.backend.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootEntity<T> {
	
	private boolean result;
	
	private String errorMessage;
	
	private T data;
	
	public static <T> RootEntity<T> ok(T data){
		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setData(data);
		rootEntity.setErrorMessage(null);
		rootEntity.setResult(true);
		return rootEntity;
	}
	public static <T> RootEntity<T> error(String errorMessage){
		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setData(null);
		rootEntity.setErrorMessage(errorMessage);
		rootEntity.setResult(false);
		return rootEntity;
	}
	public static <T> RootEntity<T> error(String errorMessage, T data){
	    RootEntity<T> rootEntity = new RootEntity<>();
	    rootEntity.setData(data); 
	    rootEntity.setErrorMessage(errorMessage);
	    rootEntity.setResult(false);
	    return rootEntity;
	}
	
}
