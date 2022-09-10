package com.brightly.storage.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class StorageFacility extends PanacheEntity implements Serializable {

    public StorageFacility() {}

    private String locationId;

    private String description;

    private boolean isActive = true;

    @Convert(converter = ListJsonConvertor.class)
    private List<String> layoutLabels = new ArrayList<>();

    public void setLayoutLabels(List<String> newLabels) {
        this.layoutLabels.clear();
        this.layoutLabels.addAll(newLabels);
    }

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<StorageLocation> locations = new HashSet<>();
}
