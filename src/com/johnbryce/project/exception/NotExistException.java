package com.johnbryce.project.exception;import com.mysql.cj.protocol.Message;

public class NotExistException extends Exception {
	
	public NotExistException(String message) {
		super("Not exist Exception "+message);
	}
}
