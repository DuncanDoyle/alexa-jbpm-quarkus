package org.jbpm.alexa.util;

public interface Environment {

	String getContainerId();
	
	String getKieServerUrl();
	
	String getTaskUser();
	
	String getKieServerUser();
	
	String getKieServerPassword();
	
}
