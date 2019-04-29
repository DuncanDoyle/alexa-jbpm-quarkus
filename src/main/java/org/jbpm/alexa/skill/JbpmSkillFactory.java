package org.jbpm.alexa.skill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;

import org.jbpm.alexa.skill.handler.CancelAndStopIntentHandler;
import org.jbpm.alexa.skill.handler.HelloWorldIntentHandler;
import org.jbpm.alexa.skill.handler.HelpIntentHandler;
import org.jbpm.alexa.skill.handler.LaunchRequestHandler;
import org.jbpm.alexa.skill.handler.SessionEndedRequestHandler;
import org.jbpm.alexa.skill.handler.jbpm.GetTaskInfoIntenHandler;
import org.jbpm.alexa.skill.handler.jbpm.GetTasksIntentHandler;
import org.jbpm.alexa.skill.handler.jbpm.ProcessTaskIntentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JbpmSkillFactory
 */
@ApplicationScoped
public class JbpmSkillFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(JbpmSkillFactory.class);

    // Inject the IntentHandlers 
    @Inject
    private GetTasksIntentHandler getTasksIntentHandler;

    @Inject
    private GetTaskInfoIntenHandler getTaskInfoIntentHandler;

    @Inject
    private ProcessTaskIntentHandler processTasksIntentHandler;

    public Skill getSkill() {

		return Skills.standard()
			.addRequestHandlers(
				new CancelAndStopIntentHandler(),
				new HelloWorldIntentHandler(),
				new HelpIntentHandler(),
				new LaunchRequestHandler(),
				new SessionEndedRequestHandler(),
				getTaskInfoIntentHandler,
				getTasksIntentHandler,
				processTasksIntentHandler)
			// Add your skill id below

			//.withSkillId("")
			.build();
       
    }
}