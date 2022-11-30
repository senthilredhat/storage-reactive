package com.brightly.storage.grpc;

import com.brightly.storage.entity.StorageFacility;
import com.brightly.storage.entity.StorageFacilityRepository;
import com.brightly.storage.utility.FGAClientConnector;
import com.google.protobuf.Int64Value;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class StorageFacilitySvc {
    @Inject
    StorageFacilityRepository storageFacilityRepository;
    @Inject
    FGAClientConnector connector;

    @ReactiveTransactional
    public Uni<Int64Value> createFacility(StorageFacility storageFacility) {
        return storageFacilityRepository.persist(storageFacility)
                .onItem()
                .transform(inserted -> Int64Value.newBuilder()
                        .setValue(inserted.id)
                        .build()).call(() -> connector.callFGA(storageFacility.getLocationId()));
    }
}
