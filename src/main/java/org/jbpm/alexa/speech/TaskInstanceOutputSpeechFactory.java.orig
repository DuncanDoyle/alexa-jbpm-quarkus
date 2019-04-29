package org.jbpm.alexa.speech;

import org.jbpm.alexa.speech.util.SpeechUtil;
import org.kie.server.api.model.instance.TaskInstance;

public class TaskInstanceOutputSpeechFactory extends GenericOutputSpeechFactory {

	public TaskInstanceOutputSpeechFactory(TaskInstance taskInstance) {
		super(buildSpeechText(taskInstance));
	}

	private static String buildSpeechText(TaskInstance taskInstance) {
		StringBuilder speechBuilder = new StringBuilder("These are the details for task with task number ")
				.append(taskInstance.getId()).append(". ");

		speechBuilder.append("This task is created by ").append(taskInstance.getCreatedBy()).append(". ");
		speechBuilder.append("The task name is: ").append(taskInstance.getName()).append(". ");
		speechBuilder.append("It's description is: ").append(taskInstance.getDescription()).append(". ");

		speechBuilder.append("It was created by process : ").append(SpeechUtil.getProcessNameFromFQN(taskInstance.getProcessId())).append(", ")
				.append("with process instance i.d.: ").append(taskInstance.getProcessInstanceId()).append(". ");

		return speechBuilder.toString();
	}

}
