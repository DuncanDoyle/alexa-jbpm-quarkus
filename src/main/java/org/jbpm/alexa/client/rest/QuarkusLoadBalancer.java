package org.jbpm.alexa.client.rest;

import java.util.Collection;
import java.util.function.Supplier;

import org.kie.server.client.balancer.LoadBalancer;

/**
 * QuarkusLoadBalancer
 */
public class QuarkusLoadBalancer extends LoadBalancer {

    private Supplier<String> urlSupplier;

    public QuarkusLoadBalancer(Collection<Supplier<String>> urlSuppliers) {
        super(new QuarkusRoundRobinBalancerStrategyWrapper(urlSuppliers));
    }

}