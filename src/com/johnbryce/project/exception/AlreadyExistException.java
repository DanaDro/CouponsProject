package com.johnbryce.project.exception;

public class AlreadyExistException extends Exception {

	public AlreadyExistException(String message) {
		super("Already exsist "+ message);
	}

}