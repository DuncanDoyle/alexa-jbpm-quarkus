package org.jbpm.alexa.client.rest;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

import java.util.function.Supplier;

import org.kie.server.client.CredentialsProvider;
import org.kie.server.common.rest.Base64Util;

/**
 * QuarkusEnteredCredentialsProvider
 */
public class QuarkusEnteredCredentialsProvider implements CredentialsProvider {

    private Supplier<String> username;

    private Supplier<String> password;

    public QuarkusEnteredCredentialsProvider(Supplier<String> username, Supplier<String> password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getHeaderName() {
        return AUTHORIZATION;
    }

    @Override
    public String getAuthorization() {
        return BASIC_AUTH_PREFIX + Base64Util.encode(username.get() + ':' + password.get());
    }
    
}