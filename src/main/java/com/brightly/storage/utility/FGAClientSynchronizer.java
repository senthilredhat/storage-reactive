package com.brightly.storage.utility;

import com.brightly.storage.entity.StorageFacility;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.inject.Inject;
import java.util.stream.IntStream;

public class FGAClientSynchronizer {
    @Inject
    Mutiny.SessionFactory factory;

    @Inject
    FGATransactionRequestHolder requestHolder;

    @ReactiveTransactional
    public Uni<Void> sendToFGA(String message) {
        System.out.println("Sending to FGA");
        return factory.withTransaction((session, txn) -> {
            if (!txn.isMarkedForRollback()) {
                IntStream.rangeClosed(3, 10).forEach(i -> {
                    try {
                        this.callFGAClient(message + "::" + i);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            return Uni.createFrom().voidItem();
        });
    }

    public void callFGAClient(String message) throws Exception {
        System.out.println("FGA Client Is called with message = " + message);
        if (message.endsWith("2")) {
            throw new Exception("Error in FGA Client");
        }
        requestHolder.addToQueue(message);
    }

    public void undoFGAClient(String message) {
        System.out.println("FGA Client Rollback called for Message = " + message);
    }

    public void undoMessages() {
        if (requestHolder.getSentMessageQueue() != null) {
            requestHolder.getSentMessageQueue().forEach(message -> {
                try {
                    undoFGAClient(message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
