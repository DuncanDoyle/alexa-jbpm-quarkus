package org.jbpm.alexa.quarkus;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.amazon.ask.model.Application;
import com.amazon.ask.model.Context;
import com.amazon.ask.model.Device;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentConfirmationStatus;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.PermissionStatus;
import com.amazon.ask.model.Permissions;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.Scope;
import com.amazon.ask.model.Session;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.SlotConfirmationStatus;
import com.amazon.ask.model.User;
import com.amazon.ask.model.interfaces.system.SystemState;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Resolutions;
import com.amazon.ask.model.slu.entityresolution.Status;
import com.amazon.ask.model.slu.entityresolution.Value;
import com.amazon.ask.model.slu.entityresolution.ValueWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.svm.core.annotate.AutomaticFeature;

import org.graalvm.nativeimage.Feature;
import org.graalvm.nativeimage.RuntimeReflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AlexaRuntimeReflectionRegistrationFeature
 */
@AutomaticFeature
public class AlexaRuntimeReflectionRegistrationFeature implements Feature {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlexaRuntimeReflectionRegistrationFeature.class);

    /**
     * Register Alexa API classes for reflection in GraalVM/SubstrateVM
     */
    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        try {
            
            System.out.println("Registering classes for runtime reflection.");

            //Class requestEnvelope = RequestEnvelope.class;
            //RuntimeReflection.register(requestEnvelope);


            //RequestEnvelope
            RuntimeReflection.register(RequestEnvelope.class);
            RuntimeReflection.register(RequestEnvelope.Builder.class);
            RuntimeReflection.register(RequestEnvelope.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(RequestEnvelope.Builder.class);

            //ResponseEnvelope
            RuntimeReflection.register(ResponseEnvelope.class);
            RuntimeReflection.register(ResponseEnvelope.Builder.class);
            RuntimeReflection.register(ResponseEnvelope.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(ResponseEnvelope.Builder.class);

            //Session
            RuntimeReflection.register(Session.class);
            RuntimeReflection.register(Session.Builder.class);
            RuntimeReflection.register(Session.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Session.Builder.class);

            //Context
            RuntimeReflection.register(Context.class);
            RuntimeReflection.register(Context.Builder.class);
            RuntimeReflection.register(Context.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Context.Builder.class);

            //Request
            //This refers to a full list of Request types .... whitelisting all of them is insane ...
            RuntimeReflection.register(Request.class);
            //We only set the IntentRequest atm 
            RuntimeReflection.register(IntentRequest.class);
            RuntimeReflection.register(IntentRequest.Builder.class);
            RuntimeReflection.register(IntentRequest.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(IntentRequest.Builder.class);

            //IntentRequest.DialogState
            RuntimeReflection.register(DialogState.class);
            
            //IntentRequest.Intent
            RuntimeReflection.register(Intent.class);
            RuntimeReflection.register(Intent.Builder.class);
            RuntimeReflection.register(Intent.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(IntentRequest.Builder.class);

            //Intent.Slot
            RuntimeReflection.register(Slot.class);
            RuntimeReflection.register(Slot.Builder.class);
            RuntimeReflection.register(Slot.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Slot.Builder.class);
            
            //Slot.SlotConfirmationStatus
            RuntimeReflection.register(SlotConfirmationStatus.class);
            
            //Slot.Resolutions
            RuntimeReflection.register(Resolutions.class);
            RuntimeReflection.register(Resolutions.Builder.class);
            RuntimeReflection.register(Resolutions.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Resolutions.Builder.class);

            RuntimeReflection.register(Resolution.class);
            RuntimeReflection.register(Resolution.Builder.class);
            RuntimeReflection.register(Resolution.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Resolution.Builder.class);

            RuntimeReflection.register(Status.class);
            RuntimeReflection.register(Status.Builder.class);
            RuntimeReflection.register(Status.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Status.Builder.class);

            RuntimeReflection.register(ValueWrapper.class);
            RuntimeReflection.register(ValueWrapper.Builder.class);
            RuntimeReflection.register(ValueWrapper.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(ValueWrapper.Builder.class);

            RuntimeReflection.register(Value.class);
            RuntimeReflection.register(Value.Builder.class);
            RuntimeReflection.register(Value.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Value.Builder.class);

            //Intent.IntentConfirmationStatus
            RuntimeReflection.register(IntentConfirmationStatus.class);
            
            //User
            RuntimeReflection.register(User.class);
            RuntimeReflection.register(User.Builder.class);
            RuntimeReflection.register(User.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(User.Builder.class);

            //User.Permissions
            RuntimeReflection.register(Permissions.class);
            RuntimeReflection.register(Permissions.Builder.class);
            RuntimeReflection.register(Permissions.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Permissions.Builder.class);

            //Permissions.Scope
            RuntimeReflection.register(Scope.class);
            RuntimeReflection.register(Scope.Builder.class);
            RuntimeReflection.register(Scope.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Scope.Builder.class);

            //Scope.PermissionStatus
            RuntimeReflection.register(PermissionStatus.class);
            
            //Permissions.Scope
            RuntimeReflection.register(Application.class);
            RuntimeReflection.register(Application.Builder.class);
            RuntimeReflection.register(Application.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Application.Builder.class);

            //Context
            RuntimeReflection.register(Context.class);
            RuntimeReflection.register(Context.Builder.class);
            RuntimeReflection.register(Context.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Context.Builder.class);

            //SystemState
            RuntimeReflection.register(SystemState.class);
            RuntimeReflection.register(SystemState.Builder.class);
            RuntimeReflection.register(SystemState.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(SystemState.Builder.class);

            //Device
            RuntimeReflection.register(Device.class);
            RuntimeReflection.register(Device.Builder.class);
            RuntimeReflection.register(Device.Builder.class.getDeclaredMethod("build"));
            RuntimeReflection.registerForReflectiveInstantiation(Device.Builder.class);
            


            /*
            Session
	User
		Permissions
			Scope
				PermissionStatus
	Application

Context
	SystemState
		Application
		
		User
			
		Device
			SupportedInterfaces
				AlexaPresentationAplInterface
					Runtime
				AudioPlayerInterface
				DisplayInterface
				VideoAppInterface
				GeolocationInterface
				
	
	
	AudioPlayerState
		PlayerActivity
	
	AutomotiveState
	
	
	DisplayState
	
	GeolocationState
		Coordinate
		Altitude
		Heading
		Speed
		LocationServices
			Status
			Access
	
	
	ViewportState
		Experience
		Shape	
		Touch
		Keyboard
            */
            
        } catch (Exception e) {
            LOGGER.error("Error while registering Amazon Alexa API classes for reflection.", e);
        }
    }


    public static class JacksonModelCrawler {

        private static final Logger LOGGER = LoggerFactory.getLogger(JacksonModelCrawler.class);

        private Set<String> visitedClasses = new HashSet<>();

        public void crawlJacksonModel(Class<?> root) {
            processClass(root);            
        }

        private void processClass(Class<?> myClass) {

            LOGGER.info("Processing model.");
            Annotation[] annotations= myClass.getDeclaredAnnotations();
            for (Annotation nextAnnotation: annotations) {
                processClassAnnotation(nextAnnotation);
            }
            
        }

        private void processClassAnnotation(Annotation annotation) {
            LOGGER.info("Processing annotation.");
            /*
            if (annotation.equals(JsonDeserialize.)) {



            }
            */
            
            
            
        }   

        private void processFieldAnnotation(Annotation annotation) {

        }
    
    }
}