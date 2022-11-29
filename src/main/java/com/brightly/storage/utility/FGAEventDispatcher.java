package com.brightly.storage.utility;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FGAEventDispatcher extends FGAClientSynchronizer {
    @ConsumeEvent(value = "fga-dispatcher")
    public Uni<Void> onExportedEvent(Object event) {
        System.out.println("fga-dispatcher Called");
        return sendToFGA((String)event);
    }
}
