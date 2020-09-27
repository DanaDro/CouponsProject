package com.johnbryce.project.exception;

public class CanNotChangeExeption extends Exception {

	public CanNotChangeExeption(String message) {
		super(message + " cannot be changed Exception.");
	}
}
