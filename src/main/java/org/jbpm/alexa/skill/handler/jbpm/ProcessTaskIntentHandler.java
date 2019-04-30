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
import org.jbpm.alexa.client.rest.KieServerClient.TaskCommand;
import org.jbpm.alexa.client.rest.UnexpectedKieServerResponseException;
import org.jbpm.alexa.speech.GenericOutputSpeechFactory;
import org.jbpm.alexa.speech.KieServerErrorOutputSpeechFactory;
import org.jbpm.alexa.speech.OutputSpeechFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProcessTaskIntentHandler
 */
@ApplicationScoped
public class ProcessTaskIntentHandler implements RequestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTaskIntentHandler.class);

	private static final String PROCESS_TASK_INTENT = "ProcessTask";

	private static final String TASK_NUMBER_SLOT = "TaskNumber";
	
	private static final String TASK_COMMAND_SLOT = "Command";

	@Inject
	private KieServerClient kieServerClient;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(PROCESS_TASK_INTENT));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        LOGGER.debug("Processing task.");

		OutputSpeechFactory<PlainTextOutputSpeech> osFactory = null;
		IntentRequest intentRequest = (IntentRequest) input.getRequest();
		Map<String, Slot> slots = intentRequest.getIntent().getSlots();
		Slot taskNumberSlot = slots.get(TASK_NUMBER_SLOT);
		Slot taskCommandSlot = slots.get(TASK_COMMAND_SLOT);
		
		if (taskNumberSlot != null && taskCommandSlot != null) {
			try {
				Long taskNumber = new Long(taskNumberSlot.getValue());
				KieServerClient.TaskCommand command = TaskCommand.valueOf(taskCommandSlot.getValue().toUpperCase());
				//osFactory = new TaskInstanceOutputSpeechFactory(kieServerClient.getTasksInfo(taskNumber));
				kieServerClient.processTask(taskNumber,command);
				osFactory = new GenericOutputSpeechFactory("Task with i.d. " + taskNumber + " successfully " + command.getSpeechText() + ".");
			} catch (UnexpectedKieServerResponseException e) {
				osFactory = new KieServerErrorOutputSpeechFactory(e);
			}
		} else {
			// We can't do much without a task number and/or command, so we need to provide
			// this output.
			// TODO: we might improve this by asking for the number again.
			osFactory = new GenericOutputSpeechFactory("Can't process the task. I didn't hear a task number or command.");
		}

		String speechText = osFactory.getSpeechText();
		return input.getResponseBuilder()
						.withSpeech(speechText)
						.withSimpleCard("ProcessTask", speechText)
						.build();
	}
}