package com.redhat.rhoar.customer.service;

import java.util.List;

import com.redhat.rhoar.customer.model.Customer;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@ProxyGen
public interface CustomerService {
	
	final public static String ADDRESS = "customer-service"; 

    void getCustomers(Handler<AsyncResult<List<Customer>>> resulthandler);

    void getCustomer(String customerId, Handler<AsyncResult<Customer>> resulthandler);

    void addCustomer(Customer customer, Handler<AsyncResult<String>> resulthandler);

    void ping(Handler<AsyncResult<String>> resultHandler);

}
