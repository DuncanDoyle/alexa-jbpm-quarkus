package org.jbpm.alexa.client.rest;

public class UnexpectedKieServerResponseException extends Exception {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	public UnexpectedKieServerResponseException() {
		super();
	}
	
	public UnexpectedKieServerResponseException(String message) {
		super(message);
	}
	
	public UnexpectedKieServerResponseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UnexpectedKieServerResponseException(Throwable cause) {
		super(cause);
	}

}
