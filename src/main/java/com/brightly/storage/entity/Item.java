package com.brightly.storage.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
public class Item extends PanacheEntity implements Serializable {

    private long itemId;
    private long siteId;
    private String costCenter;

    private String manufacturerId;
    private String manufacturerItemNo;
    private String manufacturerBarcode;
    private ItemType itemType;

    private String name;
    private String number;
    private String note;
    private String barcode;

    private String alternateItemNumber;

    private String description;
    private boolean active;

    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;

    private String createdBy;
    private String updatedBy;

    @Version
    private long version;

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", siteId=" + siteId +
                ", costCenter='" + costCenter + '\'' +
                ", manufacturerId='" + manufacturerId + '\'' +
                ", manufacturerItemNo='" + manufacturerItemNo + '\'' +
                ", manufacturerBarcode='" + manufacturerBarcode + '\'' +
                ", itemType=" + itemType +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", note='" + note + '\'' +
                ", barcode='" + barcode + '\'' +
                ", alternateItemNumber='" + alternateItemNumber + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", version=" + version +
                '}';
    }
}
