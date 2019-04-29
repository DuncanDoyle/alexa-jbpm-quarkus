package org.jbpm.alexa.skill.handler.jbpm;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.PlainTextOutputSpeech;

import org.jbpm.alexa.client.rest.KieServerClient;
import org.jbpm.alexa.client.rest.UnexpectedKieServerResponseException;
import org.jbpm.alexa.speech.KieServerErrorOutputSpeechFactory;
import org.jbpm.alexa.speech.OutputSpeechFactory;
import org.jbpm.alexa.speech.TaskSummaryListOutputSpeechFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GetTasksIntentHandler
 */
@ApplicationScoped
public class GetTasksIntentHandler implements RequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetTasksIntentHandler.class);

    private static final String GET_TASKS_INTENT = "GetTasks";
    
    @Inject
    private KieServerClient kieServerClient;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(GET_TASKS_INTENT));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        LOGGER.info("Building GetTasks response.");

		OutputSpeechFactory<PlainTextOutputSpeech> osFactory;
		try {
            LOGGER.info("Getting tasks from KIE-Server Client.");
			osFactory = new TaskSummaryListOutputSpeechFactory(kieServerClient.getTasks());
		} catch (UnexpectedKieServerResponseException e) {
            LOGGER.error("Unable to retrieve tasks from KIE-Server Client.", e);
			osFactory = new KieServerErrorOutputSpeechFactory(e);
		}

        String speechText = osFactory.getSpeechText();

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(GET_TASKS_INTENT, speechText)
                .withReprompt(speechText)
                .build();
    }

    
}