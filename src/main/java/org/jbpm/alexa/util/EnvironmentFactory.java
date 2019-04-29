package org.jbpm.alexa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentFactory {
	
	public enum EnvironmentType {
		SYSTEMPROPERTIES,
		ENVIRONMENTVARIABLES
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentFactory.class);

	private static final String ENVIRONMENT_PROPERTY_NAME = "org.jbpm.alexa.environment";
	
	private final String environmentSysProp = System.getProperty(ENVIRONMENT_PROPERTY_NAME, EnvironmentType.ENVIRONMENTVARIABLES.toString());

	private final EnvironmentType ENVIRONMENT = EnvironmentType.valueOf(environmentSysProp);
	
	private Environment environment;

	private synchronized void initEnvironment() {

		String value = System.getProperty("org.jbpm.alexa.environment");
		LOGGER.info("This is my sysprop value: " + value);


		if (environment == null) {
			LOGGER.info("EnvironmentSysPropValue is: " + environmentSysProp);
			LOGGER.info("Environment is: " + ENVIRONMENT);
			switch (ENVIRONMENT) {
				case ENVIRONMENTVARIABLES: 
					LOGGER.info("Setting EnvironmentVariables environment.");
					environment = new EnvironmentVariablesEnvironment();
					break;
				case SYSTEMPROPERTIES: 
					LOGGER.info("Setting SystemProperties environment.");
					environment = new SystemPropertiesEnvironment();
					break;
				default:
					String message = "No environment set";
					LOGGER.error(message);
					throw new RuntimeException(message);
			}
		}
	}

	public Environment getEnvironment() {
		if (environment == null) {
			initEnvironment();
		}
		return environment;
	}
}
