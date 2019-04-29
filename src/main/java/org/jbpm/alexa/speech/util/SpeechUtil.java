package org.jbpm.alexa.speech.util;

public class SpeechUtil {

	/**
	 * Returns the process name from the fully qualified process name.
	 * 
	 * @param fqProcessName
	 *            the fully qualified process name.
	 * @return the process name.
	 */
	public static String getProcessNameFromFQN(String fqProcessName) {
		return fqProcessName.substring(fqProcessName.lastIndexOf(".") + 1);
	}

}
