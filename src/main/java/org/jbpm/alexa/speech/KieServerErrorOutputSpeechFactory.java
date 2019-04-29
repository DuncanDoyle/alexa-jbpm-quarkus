package org.jbpm.alexa.speech;

import com.amazon.ask.model.ui.PlainTextOutputSpeech;

import org.jbpm.alexa.client.rest.UnexpectedKieServerResponseException;

public class KieServerErrorOutputSpeechFactory implements OutputSpeechFactory<PlainTextOutputSpeech> {

	private final UnexpectedKieServerResponseException exception;
	
	private final String speechText;
	
	public KieServerErrorOutputSpeechFactory(UnexpectedKieServerResponseException exception) {
		this.exception = exception;
		StringBuilder speechBuilder = new StringBuilder("There was a problem retrieving data from the jBPM KIE Server. ");
		speechBuilder.append(exception.getMessage());
		this.speechText = speechBuilder.toString();
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
