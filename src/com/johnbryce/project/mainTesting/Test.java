package com.johnbryce.project.mainTesting;

import java.sql.SQLException;

import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.utils.Headers;

public class Test {

	public static void main(String[] args) throws SQLException, NotExistException{
		Headers.MainTest();
		TestAllFunc.testAll();
	}
}
