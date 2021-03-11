package com.revature.app.exceptions;

@SuppressWarnings("serial")
public class NonUniqueUsernameException extends Exception {
	public NonUniqueUsernameException () {
		super("The requested username is taken.");
	}
}
