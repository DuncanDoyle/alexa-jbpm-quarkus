package org.jbpm.alexa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentVariablesEnvironment implements Environment {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentVariablesEnvironment.class);
	 
	private static final String CONTAINER_ID = System.getenv("CONTAINER_ID");
	
	private static final String KIE_SERVER_URL = System.getenv("KIE_SERVER_URL");
	
	private static final String TASK_USER = System.getenv("TASK_USER");
	
	private static final String KIE_SERVER_USER = System.getenv("KIE_SERVER_USER");
	
	private static final String KIE_SERVER_PASSWORD = System.getenv("KIE_SERVER_PASSWORD");
	
	
	@Override
	public String getContainerId() {
		//TODO: Using a container-id set via an env variable until we've sorted out authentication.
		return CONTAINER_ID;
	}

	@Override
	public String getKieServerUrl() {
		LOGGER.info("KIE-Server URL is: " + KIE_SERVER_URL);
		return KIE_SERVER_URL;
	}

	@Override
	public String getTaskUser() {
		return TASK_USER;
	}

	@Override
	public String getKieServerUser() {
		LOGGER.info("KIE-Server User is: " + KIE_SERVER_USER);
		if (KIE_SERVER_USER == null || "".equals(KIE_SERVER_USER)) {
			return "kieserver";
		}
		return KIE_SERVER_USER;
	}

	@Override
	public String getKieServerPassword() {
		LOGGER.info("KIE-Server Password is: " + KIE_SERVER_PASSWORD);
		if (KIE_SERVER_PASSWORD == null || "".equals(KIE_SERVER_PASSWORD)) {
			return "kieserver1!";
		}
		return KIE_SERVER_PASSWORD;
	}

}
