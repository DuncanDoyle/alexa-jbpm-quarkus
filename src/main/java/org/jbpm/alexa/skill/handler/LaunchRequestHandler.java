package org.jbpm.alexa.skill.handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to the Alexa Skills Kit, you can say hello";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }

    /*
    String speechText = "Welcome to the Alexa jBPM skill. You can say: get my tasks";

		// Create the Simple card content.
		// Card is displayed in the application.
		SimpleCard card = new SimpleCard();
		card.setTitle("Welcome");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		// TODO: Reprompt seems to be a prompt that is resend when the session
		// is kept open (like in an AskResponse).
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
        */
}
