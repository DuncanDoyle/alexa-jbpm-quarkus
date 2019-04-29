package org.jbpm.alexa.util;

public class SystemPropertiesEnvironment implements Environment {

	private static final String CONTAINER_ID = System.getProperty("kie.container.id");
	
	private static final String KIE_SERVER_URL = System.getProperty("kie.server.url");
	
	private static final String TASK_USER = System.getProperty("kie.server.task.user");
	
	private static final String KIE_SERVER_USER = System.getProperty("kie.server.user", "kieserver");
	
	private static final String KIE_SERVER_PASSWORD = System.getProperty("kie.server.password", "kieserver1!");
	
	@Override
	public String getContainerId() {
		return CONTAINER_ID;
	}

	@Override
	public String getKieServerUrl() {
		return KIE_SERVER_URL;
	}

	@Override
	public String getTaskUser() {
		return TASK_USER;
	}

	@Override
	public String getKieServerUser() {
		return KIE_SERVER_USER;
	}

	@Override
	public String getKieServerPassword() {
		return KIE_SERVER_PASSWORD;
	}

}
