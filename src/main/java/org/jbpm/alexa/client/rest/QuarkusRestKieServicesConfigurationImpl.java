package org.jbpm.alexa.client.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.InitialContext;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.CredentialsProvider;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.balancer.LoadBalancer;
import org.kie.server.client.jms.RequestReplyResponseHandler;
import org.kie.server.client.jms.ResponseHandler;

/**
 * Quarkus-proof implementation of KieServicesConfiguration.
 * 
 * As we need to statically initialize the KieServerClient in order for GraalVM native image compilation to work (due to dependencies our KIE-Server Client implementation
 * brings in), we need to allow for static initialization of this class, and defer rertrieaval of URL, username and password to runtime.
 * 
 * <ul>
 * <li>Defer retrieval of URL to runtime.</li>
 * <li>Defer retrieval of username and password to runtime using provider functions.</li>
 * </ul>
 * 
 * DISCLAIMER: WE ONLY SUPPORT REST FOR NOW!!!!!
 * 
 */
public class QuarkusRestKieServicesConfigurationImpl implements KieServicesConfiguration {

    /*
    public static final String SSL_CONNECTION_FACTORY_NAME = "jms/SslRemoteConnectionFactory";
    public static final String CONNECTION_FACTORY_NAME     = "jms/RemoteConnectionFactory";
    public static final String REQUEST_QUEUE_NAME          = "jms/queue/KIE.SERVER.REQUEST";
    public static final String RESPONSE_QUEUE_NAME         = "jms/queue/KIE.SERVER.RESPONSE";
    */

    private             long timeoutInMillisecs      = 10000; // in milliseconds

    // REST or JMS
    private Transport transport = Transport.REST;

    // General
    //private String userName;
    //private String password;
    //private String serverUrl;

    //Lazy initializers.
    private Supplier<String> userName;
    private Supplier<String> password;
    private Supplier<String> serverUrl;

    private List<String> capabilities;

    // JMS
    /*
    private boolean useSsl = false;
    private ConnectionFactory connectionFactory;
    private Queue             requestQueue;
    private Queue             responseQueue;
    */
    private ResponseHandler responseHandler = new RequestReplyResponseHandler();
    //TODO: Only needed for JMS, but it seems that the clients retrieve this handler in the constructor of AbstractKieServicesClientImpl ...
    /*
    private boolean jmsTransactional = false;
    */

    //We only support JSON atm
    private MarshallingFormat format = MarshallingFormat.JSON;
    
    private Set<Class<?>>     extraClasses = new HashSet<Class<?>>();

    private CredentialsProvider credentialsProvider;

    private LoadBalancer loadBalancer;

    private Map<String, String> headers;

    /*
     * Public constructors and setters
     */
    public QuarkusRestKieServicesConfigurationImpl(Supplier<String> url, Supplier<String> userName, Supplier<String> password) {
        this(url, userName, password, 5000);
    }

    /*
     * Public constructors and setters
     */
    public QuarkusRestKieServicesConfigurationImpl(Supplier<String> url, Supplier<String> userName, Supplier<String> password, long timeout) {
        Collection<Supplier<String>> urlSuppliers = new ArrayList<>();
        urlSuppliers.add(url);
        this.loadBalancer = new QuarkusLoadBalancer(urlSuppliers);
        this.userName = userName;
        this.password = password;
        this.serverUrl = url;
        this.timeoutInMillisecs = timeout;
        this.credentialsProvider = new QuarkusEnteredCredentialsProvider(userName, password);
    }

    /**
     * REST based constructor
     * @param url
     * @param username
     * @param password
     */
    /*
    public QuarkusRestKieServicesConfigurationImpl(String url, String username, String password) {
        this( url, username, password, 5000 );
    }
    */

    /**
     * REST based constructor
     * @param url
     * @param username
     * @param password
     * @param timeout the maximum timeout in milliseconds
     */
    /*
    public QuarkusRestKieServicesConfigurationImpl(String url, String username, String password, long timeout) {
        this.transport = Transport.REST;

        this.serverUrl = url;
        this.userName = username;
        this.password = password;
        this.timeoutInMillisecs = timeout;
        this.credentialsProvider = new EnteredCredentialsProvider(username, password);
    }
    */

    /**
     * REST based constructor
     * @param url
     * @param credentialsProvider
     */
    /*
    public QuarkusRestKieServicesConfigurationImpl(String url, CredentialsProvider credentialsProvider) {
        this( url, credentialsProvider, 5000 );
    }
    */

    /**
     * REST based constructor
     * @param url
     * @param credentialsProvider
     * @param timeout the maximum timeout in milliseconds
     */
    /*
    public QuarkusRestKieServicesConfigurationImpl(String url, CredentialsProvider credentialsProvider, long timeout) {
        this.transport = Transport.REST;

        this.serverUrl = url;
        this.timeoutInMillisecs = timeout;
        this.credentialsProvider = credentialsProvider;
    }
    */

    @Override
    public void dispose() {
        if ( extraClasses != null ) {
            extraClasses.clear();
            extraClasses = null;
        }
        /*
        if ( connectionFactory != null ) {
            connectionFactory = null;
        }
        if ( requestQueue != null ) {
            requestQueue = null;
        }
        if ( responseQueue != null ) {
            responseQueue = null;
        }
        */
    }

    // REST ----------------------------------------------------------------------------------------------------------------------

    // JMS ----------------------------------------------------------------------------------------------------------------------
    /*
    public KieServicesConfigurationImpl(ConnectionFactory connectionFactory, Queue requestQueue, Queue responseQueue) {
        this.transport = Transport.JMS;
        this.connectionFactory = connectionFactory;
        this.requestQueue = requestQueue;
        this.responseQueue = responseQueue;
        this.credentialsProvider = null;
        checkValidValues( this.connectionFactory, this.requestQueue, this.responseQueue );
    }

    public KieServicesConfigurationImpl(
            ConnectionFactory connectionFactory, Queue requestQueue,
            Queue responseQueue, String username, String password) {
        this( connectionFactory, requestQueue, responseQueue );
        setAndCheckUserNameAndPassword( username, password );
    }

    public KieServicesConfigurationImpl(InitialContext context, String username, String password) {
        this.transport = Transport.JMS;
        setAndCheckUserNameAndPassword( username, password );
        setRemoteInitialContext( context );
        this.credentialsProvider = new EnteredCredentialsProvider(username, password);

    }
    */

    /*
    public void checkValidJmsValues() {
        checkValidValues( connectionFactory, requestQueue, responseQueue );
    }
    */

    /*
    private static void checkValidValues(ConnectionFactory connectionFactory, Queue requestQueue, Queue responseQueue)
            throws IllegalStateException {
        if ( connectionFactory == null ) {
            throw new IllegalStateException( "The connection factory argument may not be null." );
        }
        if ( requestQueue == null ) {
            throw new IllegalStateException( "At least a ksession queue or task queue is required." );
        }
        if ( responseQueue == null ) {
            throw new IllegalStateException( "The response queue argument may not be null." );
        }
    }
    */

    
    @Override
    public KieServicesConfiguration setRemoteInitialContext(InitialContext context) {
        /*
        String prop = CONNECTION_FACTORY_NAME;
        try {
            if ( this.connectionFactory == null ) {
                this.connectionFactory = (ConnectionFactory) context.lookup( prop );
            }
            prop = REQUEST_QUEUE_NAME;
            this.requestQueue = (Queue) context.lookup( prop );
            prop = RESPONSE_QUEUE_NAME;
            this.responseQueue = (Queue) context.lookup( prop );
        } catch ( NamingException ne ) {
            throw new KieServicesException( "Unable to retrieve object for " + prop, ne );
        }
        checkValidValues( connectionFactory, requestQueue, responseQueue );
        return this;
        */
        throw new UnsupportedOperationException();
    }
    

    /*
    private KieServicesConfiguration setAndCheckUserNameAndPassword(String username, String password) {
        if ( username == null || username.trim().isEmpty() ) {
            throw new IllegalArgumentException( "The user name may not be empty or null." );
        }
        this.userName = username;
        if ( password == null ) {
            throw new IllegalArgumentException( "The password may not be null." );
        }
        this.password = password;
        return this;
    }
    */

    /**
     * (Package-scoped) Getters
     */
    @Override
    public MarshallingFormat getMarshallingFormat() {
        return format;
    }

    @Override
    public KieServicesConfiguration setMarshallingFormat(MarshallingFormat format) {
        this.format = format;
        return this;
    }

    @Override
    public boolean isJms() {
        return (this.transport == Transport.JMS);
    }

    @Override
    public boolean isRest() {
        return (this.transport == Transport.REST);
    }

    @Override
    public String getServerUrl() {
        return serverUrl.get();
    }

    @Override
    public String getUserName() {
        return userName.get();
    }

    @Override
    public String getPassword() {
        return password.get();
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        //return connectionFactory;
        return null;
    }

    @Override
    public Queue getRequestQueue() {
        //return requestQueue;
        return null;
    }

    @Override
    public Queue getResponseQueue() {
        //return responseQueue;
        return null;
    }

    @Override
    public boolean addExtraClasses(Set<Class<?>> extraClassList) {
        return this.extraClasses.addAll( extraClassList );
    }

    @Override
    public KieServicesConfiguration clearExtraClasses() {
        this.extraClasses.clear();
        return this;
    }

    @Override
    public Set<Class<?>> getExtraClasses() {
        return this.extraClasses;
    }

    @Override
    public Transport getTransport() {
        return this.transport;
    }

    @Override
    public long getTimeout() {
        return timeoutInMillisecs;
    }

    @Override
    public boolean getUseUssl() {
        //return useSsl;
        throw new UnsupportedOperationException();
    }

    // Setters -------------------------------------------------------------------------------------------------------------------

    @Override
    public KieServicesConfiguration setTimeout(long timeout) {
        this.timeoutInMillisecs = timeout;
        return this;
    }

    @Override
    public KieServicesConfiguration setServerUrl(String url) {
        this.serverUrl = () -> url;
        return this;
    }

    @Override
    public KieServicesConfiguration setUserName(String userName) {
        this.userName = () -> userName;
        /*
        if (credentialsProvider instanceof EnteredCredentialsProvider) {
            ((EnteredCredentialsProvider) credentialsProvider).setUsername(userName);
        }
        */
        return this;

    }

    @Override
    public KieServicesConfiguration setPassword(String password) {
        this.password = () -> password;
        /*
        if (credentialsProvider instanceof EnteredCredentialsProvider) {
            ((EnteredCredentialsProvider) credentialsProvider).setPassword(password);
        }
        */
        return this;
    }

    @Override
    public KieServicesConfiguration setExtraClasses(Set<Class<?>> extraJaxbClasses) {
        this.extraClasses.clear();
        if (extraJaxbClasses != null) {
            this.extraClasses.addAll(extraJaxbClasses);
        }
        return this;
    }

    @Override
    public KieServicesConfiguration setConnectionFactory(ConnectionFactory connectionFactory) {
        //this.connectionFactory = connectionFactory;
        //return this;
        throw new UnsupportedOperationException();
        
    }

    @Override
    public KieServicesConfiguration setRequestQueue(Queue requestQueue) {
        //this.requestQueue = requestQueue;
        //return this;
        throw new UnsupportedOperationException(); 
    }

    @Override
    public KieServicesConfiguration setResponseQueue(Queue responseQueue) {
        //this.responseQueue = responseQueue;
        //return this;
        throw new UnsupportedOperationException();
        
    }

    @Override
    public KieServicesConfiguration setUseSsl(boolean useSsl) {
        //this.useSsl = useSsl;
        //return this;
        throw new UnsupportedOperationException();
        
    }

    @Override
    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    @Override
    public List<String> getCapabilities() {
        return this.capabilities;
    }

    @Override
    public void setCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    @Override
    public CredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }

    @Override
    public void setLoadBalancer(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    @Override
    public LoadBalancer getLoadBalancer() {
        return this.loadBalancer;
    }

    @Override
    public void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public ResponseHandler getResponseHandler() {
        return this.responseHandler;
    }

    @Override
    public boolean isJmsTransactional() {
        //return jmsTransactional;
        throw new UnsupportedOperationException();
    }

    @Override
    public void setJmsTransactional(boolean jmsTransactional) {
        throw new UnsupportedOperationException();
        //this.jmsTransactional = jmsTransactional;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }


    // Clone ---
    private QuarkusRestKieServicesConfigurationImpl(QuarkusRestKieServicesConfigurationImpl config) {
        //this.connectionFactory = config.connectionFactory;

        this.extraClasses = config.extraClasses;
        this.format = config.format;
        //this.requestQueue = config.requestQueue;
        this.password = config.password;
        //this.responseQueue = config.responseQueue;
        this.serverUrl = config.serverUrl;
        this.timeoutInMillisecs = config.timeoutInMillisecs;
        this.transport = config.transport;
        this.userName = config.userName;
        //this.useSsl = config.useSsl;
        this.capabilities = config.capabilities;
        this.credentialsProvider = config.credentialsProvider;
        this.loadBalancer = config.loadBalancer;
        //this.responseHandler = config.responseHandler;
        //this.jmsTransactional = config.jmsTransactional;
        this.headers = config.headers;
    }

    @Override
    public KieServicesConfiguration clone() {
        return new QuarkusRestKieServicesConfigurationImpl( this );
    }

    @Override
    public String toString() {
        return "KieServicesConfiguration{" +
                "transport=" + transport +
                ", serverUrl='" + serverUrl + '\'' +
                '}';
    }

    /*
     * Deprecated methods
     */
    @Deprecated
    @Override
    public Set<Class<?>> getExtraJaxbClasses() {
        return getExtraClasses();
    }

    @Deprecated
    @Override
    public boolean addJaxbClasses(Set<Class<?>> extraJaxbClassList) {
        return addExtraClasses(extraJaxbClassList);
    }

    @Deprecated
    @Override
    public KieServicesConfiguration setExtraJaxbClasses(Set<Class<?>> extraJaxbClasses) {
        return setExtraClasses(extraJaxbClasses);
    }

    @Deprecated
    @Override
    public KieServicesConfiguration clearJaxbClasses() {
        return clearExtraClasses();
    }
}