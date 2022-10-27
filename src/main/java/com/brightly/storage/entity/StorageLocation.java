package com.brightly.storage.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Convert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class StorageLocation extends PanacheEntity implements Serializable {

    public StorageLocation() {}

    @ManyToOne
    private StorageFacility facility;

    @Convert(converter = ListJsonConvertor.class)
    private List<String> layoutValues = new ArrayList<>();

    private boolean isActive = true;
}
