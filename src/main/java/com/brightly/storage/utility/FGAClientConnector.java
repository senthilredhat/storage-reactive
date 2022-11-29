package com.brightly.storage.utility;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FGAClientConnector {
    @Inject
    EventBus bus;
    public Uni<Object> callFGA(String message) {
        System.out.println("Sending to FGA");
        return bus.<Object> request("fga-dispatcher",message).onItem().transform(response -> response.body());
    }
}
