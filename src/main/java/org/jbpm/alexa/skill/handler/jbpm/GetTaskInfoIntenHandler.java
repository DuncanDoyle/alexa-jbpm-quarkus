package org.jbpm.alexa.skill.handler.jbpm;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.ui.PlainTextOutputSpeech;
import com.amazon.ask.model.ui.SimpleCard;

import org.jbpm.alexa.client.rest.KieServerClient;
import org.jbpm.alexa.client.rest.UnexpectedKieServerResponseException;
import org.jbpm.alexa.speech.GenericOutputSpeechFactory;
import org.jbpm.alexa.speech.KieServerErrorOutputSpeechFactory;
import org.jbpm.alexa.speech.OutputSpeechFactory;
import org.jbpm.alexa.speech.TaskInstanceOutputSpeechFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GetTaskInfoIntenHandler
 */
@ApplicationScoped
public class GetTaskInfoIntenHandler implements RequestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetTaskInfoIntenHandler.class);

	private static final String GET_TASK_INFO_INTENT = "GetTaskInfo";

	private static final String TASK_NUMBER_SLOT = "TaskNumber";
	
	@Inject
    private KieServerClient kieServerClient;

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(GET_TASK_INFO_INTENT));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		OutputSpeechFactory<PlainTextOutputSpeech> osFactory = null;
		IntentRequest intentRequest = (IntentRequest) input.getRequest();
		Map<String, Slot> slots = intentRequest.getIntent().getSlots();
		Slot taskNumberSlot = slots.get(TASK_NUMBER_SLOT);
		
		if (taskNumberSlot != null) {
			try {
				Long taskNumber = new Long(taskNumberSlot.getValue());
				osFactory = new TaskInstanceOutputSpeechFactory(kieServerClient.getTasksInfo(taskNumber));
			} catch (UnexpectedKieServerResponseException e) {
				osFactory = new KieServerErrorOutputSpeechFactory(e);
			}
		} else {
			// We can't do much without a task number, so we need to provide
			// this output.
			// TODO: we might improve this by asking for the number again.
			osFactory = new GenericOutputSpeechFactory("Can't retrieve task info. I didn't hear a task number.");
		}

		String speechText = osFactory.getSpeechText();

		return input.getResponseBuilder()
						.withSpeech(speechText)
						.withSimpleCard("GetTaskInfo", speechText)
						.build();
	}
}   