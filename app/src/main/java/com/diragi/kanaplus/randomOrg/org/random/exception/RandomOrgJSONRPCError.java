package com.diragi.kanaplus.randomOrg.org.random.exception;

public class RandomOrgJSONRPCError extends RuntimeException {

	/** Constructs a new exception with the specified detail message.
	 **
	 ** @param message @see java.lang.Exception#Exception(java.lang.String) 
	 **/
	public RandomOrgJSONRPCError(String message) {
		super(message);
	}
}