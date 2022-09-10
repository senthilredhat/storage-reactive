package com.brightly.storage.entity;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StorageFacilityRepository implements PanacheRepository<StorageFacility> {

}
