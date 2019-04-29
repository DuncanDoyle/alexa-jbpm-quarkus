package org.jbpm.alexa.client.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.kie.server.client.balancer.BalancerStrategy;
import org.kie.server.client.balancer.impl.RoundRobinBalancerStrategy;

/**
 * QuarkusRoundRobinBalancerStrategy.
 * 
 * Initialization of the BalancerStrategy is deferred until first access. This is to allow static initialization during Quarkus native image compiltation, while
 * at the same time allow configuration of URLs at runtime.
 */
public class QuarkusRoundRobinBalancerStrategyWrapper implements BalancerStrategy {

    private boolean initialized = false;

    private BalancerStrategy balancerStrategy = null;

    Collection<Supplier<String>> urlSuppliers;

    public QuarkusRoundRobinBalancerStrategyWrapper(Collection<Supplier<String>> urlSuppliers) {
        //Deferring real initialization to runtime first usage.
        this.urlSuppliers = urlSuppliers;
    }


    private synchronized void initialize() {
        if (!initialized) { 
            Collection<String> urls = new ArrayList<String>();
            for(Supplier<String> nextUrlSupplier: urlSuppliers) {
                urls.add(nextUrlSupplier.get());
            }
            balancerStrategy = new RoundRobinBalancerStrategy(urls);
            initialized = true;
        }  
    }

    @Override
    public String next() {
        if (!initialized) initialize();
        return balancerStrategy.next();
    }

    @Override
    public String markAsOffline(String url) {
        if (!initialized) initialize();
        return balancerStrategy.markAsOffline(url);
    }

    @Override
    public String markAsOnline(String url) {
        if (!initialized) initialize();
        return balancerStrategy.markAsOnline(url);
    }

    @Override
    public List<String> getAvailableEndpoints() {
        if (!initialized) initialize();
        return balancerStrategy.getAvailableEndpoints();
    }

}