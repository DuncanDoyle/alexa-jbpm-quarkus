package org.jbpm.alexa;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.ask.exception.AskSdkException;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.services.Serializer;
import com.amazon.ask.servlet.ServletConstants;
import com.amazon.ask.servlet.SkillServlet;
import com.amazon.ask.util.JacksonSerializer;

import org.apache.commons.io.IOUtils;
import org.jbpm.alexa.skill.JbpmSkillFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main servlet for the jBPM Alexa interface.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
@WebServlet("/speech")
public class AlexaJbpmSkillServlet extends SkillServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaJbpmSkillServlet.class);

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public AlexaJbpmSkillServlet(JbpmSkillFactory skillFactory) {
		super(skillFactory.getSkill());
		LOGGER.info("Bootstrapping jBPM Alexa Skill.");
	}

	/**
	 * Just overriding to do some logging.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//LOGGER.info("Incoming http request: " + request.get;
		/* Only use this to debug, as it makes the request fail).*/
		/*
		String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		System.out.println(test);
		*/
		/*
		Serializer serializer = new JacksonSerializer();
		byte[] serializedRequestEnvelope = IOUtils.toByteArray(request.getInputStream());
        try {

			String requestEnvelope = IOUtils.toString(serializedRequestEnvelope, ServletConstants.CHARACTER_ENCODING);



            final RequestEnvelope deserializedRequestEnvelope = serializer.deserialize(requestEnvelope, RequestEnvelope.class);

			// Verify the authenticity of the request by executing configured verifiers.
			Request dsRequest = deserializedRequestEnvelope.getRequest();
			LOGGER.info("Request locale is : " + dsRequest.getLocale());
			LOGGER.info("Request timestamp is:  " + dsRequest.getTimestamp());

			

            
        } catch (AskSdkException ex) {
            int statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            LOGGER.error("Exception occurred in doPost, returning status code {}", statusCode, ex);
            response.sendError(statusCode, ex.getMessage());
		}
		*/
		super.doPost(request, response);

	}

	

}
