package com.revature.exceptions;

@SuppressWarnings("serial")
public class CatAlreadyAdoptedException extends Exception {
	public CatAlreadyAdoptedException () {
		super("The user tried to adopt a cat that is already adopted.");
	}
}
