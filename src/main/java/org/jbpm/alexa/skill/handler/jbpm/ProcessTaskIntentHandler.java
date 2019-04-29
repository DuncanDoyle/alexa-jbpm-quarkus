package org.jbpm.alexa.skill.handler.jbpm;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * ProcessTaskIntentHandler
 */
@ApplicationScoped
public class ProcessTaskIntentHandler implements RequestHandler {

    private static final String GET_TASK_INFO_INTENT = "ProcessTask";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(GET_TASK_INFO_INTENT));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return null;
    }

    /*
    LOGGER.debug("Processing task.");

		OutputSpeechFactory<PlainTextOutputSpeech> osFactory = null;
		
		Map<String, Slot> slots = intent.getSlots();
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

		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("ProcessTask");
		card.setContent(osFactory.getSpeechText());

        return SpeechletResponse.newTellResponse(osFactory.getOutputSpeech(), card);
        */

    
}