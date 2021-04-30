package com.example.requestvacation.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	public CustomException() {
        super();
}   
	public CustomException(String msg) {
	        super(msg);
	}     
}
