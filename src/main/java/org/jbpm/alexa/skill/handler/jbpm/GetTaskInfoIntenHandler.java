package org.jbpm.alexa.skill.handler.jbpm;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GetTaskInfoIntenHandler
 */
@ApplicationScoped
public class GetTaskInfoIntenHandler implements RequestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetTaskInfoIntenHandler.class);

	private static final String GET_TASK_INFO_INTENT = "GetTaskInfo";

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(GET_TASK_INFO_INTENT));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return null;
	}

	/*
	LOGGER.debug("Building GetTaskInfo response.");

		OutputSpeechFactory<PlainTextOutputSpeech> osFactory = null;
		
		Map<String, Slot> slots = intent.getSlots();
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

		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("GetTasksInfo");
		card.setContent(osFactory.getSpeechText());

		return SpeechletResponse.newTellResponse(osFactory.getOutputSpeech(), card);
		*/


}   