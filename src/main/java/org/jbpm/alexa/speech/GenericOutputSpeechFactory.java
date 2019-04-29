package org.jbpm.alexa.speech;

import com.amazon.ask.model.ui.PlainTextOutputSpeech;

public class GenericOutputSpeechFactory implements OutputSpeechFactory<PlainTextOutputSpeech> {

	private final String speechText;
	
	public GenericOutputSpeechFactory(String speechText) {
		this.speechText = speechText;
	}
	
	@Override
	public PlainTextOutputSpeech getOutputSpeech() {
		PlainTextOutputSpeech.Builder outputSpeechBuilder = PlainTextOutputSpeech.builder();
		return outputSpeechBuilder.withText(getSpeechText()).build();
	}

	@Override
	public String getSpeechText() {
		return speechText;
	}
}
