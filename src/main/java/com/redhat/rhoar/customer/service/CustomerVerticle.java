package com.redhat.rhoar.customer.service;


import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ProxyHelper;

public class CustomerVerticle extends AbstractVerticle {
    
    private CustomerService customerService;

	@Inject
    public CustomerVerticle(@Named("mongo") CustomerService customerService) {
        this.customerService = customerService;
    }
	
    @Override
    public void start(Future<Void> startFuture) throws Exception {
                
        //----
        // * Register the service on the event bus
        // * Complete the future
        //----
        ProxyHelper.registerService(CustomerService.class, vertx, customerService, CustomerService.ADDRESS);

        startFuture.complete();
    }

    @Override
    public void stop() throws Exception {
    }

}
