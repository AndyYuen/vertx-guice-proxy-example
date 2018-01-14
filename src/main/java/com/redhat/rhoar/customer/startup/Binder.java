package com.redhat.rhoar.customer.startup;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.redhat.rhoar.customer.service.CustomerService;
import com.redhat.rhoar.customer.service.CustomerServiceMongoImpl;
import com.redhat.rhoar.customer.service.CustomerServiceVertxEBProxy;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class Binder extends AbstractModule {


	@Provides @Singleton
    public MongoClient provideMongoClient(Vertx vertx, JsonObject config){
		System.out.println("Calling provideMongoClient...");
        return MongoClient.createShared(vertx, AppConfig.getInstance(vertx).getConfig());
    }
	
	@Provides @Singleton
	@Named("mongo")
    public CustomerService provideCustomerService(MongoClient client){
		System.out.println("Calling provideCustomerService...");
        return new CustomerServiceMongoImpl(client);
    }
	
	@Provides @Singleton
	@Named("proxy")
    public CustomerService provideCustomerServiceProxy(Vertx vertx, String address) {
		System.out.println("Calling provideCustomerServiceProxy...");
		return new CustomerServiceVertxEBProxy(vertx, CustomerService.ADDRESS);
    }
	
	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	}

}
