package org.jbpm.alexa.quarkus;

import com.amazon.ask.model.RequestEnvelope;

import org.junit.Test;

/**
 * JacksonModelCrawlerTest
 */
public class JacksonModelCrawlerTest {

    

    @Test
    public void testModelCrawler() {
        AlexaRuntimeReflectionRegistrationFeature.JacksonModelCrawler modelCrawler = new AlexaRuntimeReflectionRegistrationFeature.JacksonModelCrawler();

        modelCrawler.crawlJacksonModel(RequestEnvelope.class);

    }
}