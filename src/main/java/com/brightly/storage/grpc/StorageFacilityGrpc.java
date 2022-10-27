package com.brightly.storage.grpc;

import com.brightly.storage.entity.StorageFacility;
import com.brightly.storage.entity.StorageFacilityRepository;
import com.brightly.storage.entity.StorageLocation;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.quarkus.grpc.GrpcService;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@GrpcService
public class StorageFacilityGrpc
        extends MutinyStorageFacilityServiceGrpc.StorageFacilityServiceImplBase {

    @Inject
    StorageFacilityRepository storageFacilityRepository;

    @Override
    public Uni<Int64Value> createFacility(StorageFacilityRequest request) {

        return Panache
                .withTransaction(() -> {
                            StorageFacility storageFacility = new StorageFacility();
                            storageFacility.setLocationId(request.getLocationId());
                            storageFacility.setDescription(request.getDescription());
                            storageFacility.setLayoutLabels(request.getLayoutLabelsList());
                            return storageFacilityRepository.persist(storageFacility);})
                .onItem()
                .transform(inserted -> Int64Value.newBuilder()
                                .setValue(inserted.id)
                                .build());
    }

    @Transactional
    @Blocking
    public Uni<Int64Value> createFacilityBlocking(StorageFacilityRequest request) {

        StorageFacility storageFacility = new StorageFacility();
        storageFacility.setLocationId(request.getLocationId());
        storageFacility.setDescription(request.getDescription());
        storageFacility.setLayoutLabels(request.getLayoutLabelsList());

        return storageFacilityRepository.persistAndFlush(storageFacility)
                .onItem()
                .transform(inserted ->
                        Int64Value.newBuilder().setValue(inserted.id).build());

    }

    public Uni<StringValue> updateFacility(StorageFacilityRequest request) {

        return storageFacilityRepository
                .find("location_id = ?1", request.getLocationId())
                .firstResult()
                .onItem()
                .call(storageFacility ->
                        Panache.withTransaction(() -> {
                            storageFacility.setDescription(request.getDescription());
                            storageFacility.setLayoutLabels(request.getLayoutLabelsList());

                            return storageFacilityRepository.persist(storageFacility);
                        }))
                .onItem()
                .transform(facility -> StringValue.newBuilder().setValue(facility.getLocationId()).build());
    }

    @Transactional
    @Override
    public Uni<StorageFacilityResponse> getFacilityByLocation(StringValue request) {
        return storageFacilityRepository
                .find("location_id = ?1", request.getValue())
                .firstResult()
                .onItem()
                .transform(facility -> StorageFacilityResponse.newBuilder()
                            .setDescription(facility.getDescription())
                            .addAllLayoutLabels(facility.getLayoutLabels())
                            .addAllStorageLocationId(
                                facility.getLocations()
                                        .stream()
                                        .map(location -> location.id)
                                        .collect(Collectors.toList()))
                            .setIsActive(facility.isActive()).build());
    }

    @Override
    public Uni<StorageFacilityResponse> getFacilityById(Int64Value request) {
        return storageFacilityRepository
                .findById(request.getValue())
                .onItem()
                .transform(facility -> StorageFacilityResponse.newBuilder()
                            .setDescription(facility.getDescription())
                            .addAllLayoutLabels(facility.getLayoutLabels())
                            .addAllStorageLocationId(
                                    facility.getLocations()
                                            .stream()
                                            .map(location -> location.id)
                                            .collect(Collectors.toList()))
                            .setIsActive(facility.isActive()).build());
    }

    @Override
    public Uni<Int64Value> createStorageLocation(StorageLocationRequest request) {
        return storageFacilityRepository
                .find("location_id = ?1", request.getLocationId())
                .firstResult().onItem()
                .call(storageFacility ->
                    Panache.withTransaction(() -> {
                                var storageLocation = new StorageLocation();
                                storageLocation.setLayoutValues(
                                        request.getLayoutValuesList());
                                storageLocation.setActive(true);
                                storageLocation.setFacility(storageFacility);
                                storageFacility.getLocations().add(storageLocation);
                                return storageFacilityRepository.persist(storageFacility);
                            }))
                .onItem()
                .transform(inserted -> Int64Value.newBuilder().setValue(inserted.id).build());
    }

    @Override
    public Uni<StringValue> updateStorageLocation(StorageLocationUpdateRequest request) {

        return storageFacilityRepository
                .find("location_id = ?1", request.getLocationId())
                .firstResult()
                .onItem()
                .call(storageFacility ->
                        Panache.withTransaction(() -> {
                            var storageLocation = storageFacility.getLocations()
                                    .stream()
                                    .filter(l -> l.getLayoutValues().equals(request.getCurrentLayoutValuesList()))
                                    .findFirst().orElse(null);

                            storageLocation.setLayoutValues(
                                    request.getNewLayoutValuesList());
                            return storageFacilityRepository.persist(storageFacility);
                        }))
                .onItem()
                .transform(facility -> StringValue.newBuilder().setValue(facility.getLocationId()).build());
    }

    public Uni<StorageLocationResponse> listStorageLocationsForFacility(Int64Value request) {
        return storageFacilityRepository
                .findById(request.getValue())
                .onItem()
                .transform(facility -> StorageLocationResponse.newBuilder()
                        .addAllStorageLocations(
                                facility.getLocations()
                                        .stream()
                                        .map(storageLocation ->
                                            com.brightly.storage.grpc.StorageLocation.newBuilder()
                                                    .setStorageLocationId(storageLocation.id)
                                                    .addAllLayoutValues(storageLocation.getLayoutValues())
                                                    .setIsActive(storageLocation.isActive())
                                                    .build()
                                        )
                                        .collect(Collectors.toList()))
                        .build());
    }

}
