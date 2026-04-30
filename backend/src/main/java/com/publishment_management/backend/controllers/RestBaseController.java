package com.publishment_management.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.publishment_management.backend.models.RootEntity;

public abstract class RestBaseController {

    public <T> ResponseEntity<RootEntity<T>> ok(T data) {
        return ResponseEntity.ok(RootEntity.ok(data));
    }

    public <T> ResponseEntity<RootEntity<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(RootEntity.ok(data));
    }
    

}
