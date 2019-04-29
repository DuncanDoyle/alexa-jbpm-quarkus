package org.jbpm.alexa.speech;

import java.util.List;
import java.util.stream.Collectors;

import org.jbpm.alexa.speech.util.SpeechUtil;
import org.kie.server.api.model.instance.TaskSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskSummaryListOutputSpeechFactory extends GenericOutputSpeechFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskSummaryListOutputSpeechFactory.class);

	private final static int taskPageSize = 4;

	public TaskSummaryListOutputSpeechFactory(List<TaskSummary> taskSummaries) {
		super(buildSpeechText(taskSummaries));
	}
	
	/**
	 * Builds the speech text.
	 * 
	 * @param taskSummaries
	 * @return
	 */
	private static String buildSpeechText(List<TaskSummary> taskSummaries) {
		
		StringBuilder speechBuilder = new StringBuilder("You have " + taskSummaries.size() + " tasks in your inbox. ");
		
		speechBuilder.append("These are the first ")
				.append((taskSummaries.size() <= taskPageSize) ? taskSummaries.size() : taskPageSize)
				.append(" tasks. ");

		//Some functional stuff to traverse the list.
		speechBuilder.append(taskSummaries.stream().limit(taskPageSize).map(t -> {
			StringBuilder taskSpeechBuilder = new StringBuilder();
			taskSpeechBuilder.append("Task with i.d. ").append(t.getId()).append(", ");
			taskSpeechBuilder.append("has Name ").append(t.getName()).append(", ");
			
			taskSpeechBuilder.append("has Process i.d. ").append(SpeechUtil.getProcessNameFromFQN(t.getProcessId())).append(", ");

			taskSpeechBuilder.append("has Priority ").append(t.getPriority()).append(". ");
			return taskSpeechBuilder.toString();
		}).collect(Collectors.joining()));
		
		return speechBuilder.toString();
	}

}
