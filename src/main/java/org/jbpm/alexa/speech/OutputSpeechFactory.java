package org.jbpm.alexa.speech;

import com.amazon.ask.model.ui.OutputSpeech;

public interface OutputSpeechFactory<T extends OutputSpeech> {

	T getOutputSpeech();
	
	String getSpeechText();
	
}
