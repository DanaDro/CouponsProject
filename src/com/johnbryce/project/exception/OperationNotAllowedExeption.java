package com.johnbryce.project.exception;

public class OperationNotAllowedExeption extends Exception {
	
	public OperationNotAllowedExeption(String message) {
		super(message + " is not allowed.");
	}

}
