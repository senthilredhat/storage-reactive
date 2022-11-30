package com.brightly.storage.utility;

import com.brightly.storage.entity.StorageFacility;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.inject.Inject;

public class FGAClientSynchronizer {
    @Inject
    Mutiny.SessionFactory factory;

    @ReactiveTransactional
    public Uni<Void> sendToFGA(String message) {
        System.out.println("Sending to FGA");
        return factory.withTransaction((session, txn) -> {
                if (!txn.isMarkedForRollback()) {
                    try {
                        callFGAClient(message);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            return Uni.createFrom().voidItem();
        });
    }

    public void callFGAClient(String message) throws Exception {
        System.out.println("FGA Client Is called");
        if (message.endsWith("2")) {
            throw new Exception("Error in FGA Client");
        }
    }
}
