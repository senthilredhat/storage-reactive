package com.brightly.storage.kafka.model;

import java.time.OffsetDateTime;

public class Part {
    private long partId;
    private long siteId;
    private String costCenterId;
    private String name;
    private String partNo;
    private String alternatePartNo;
    private String barcode;
    private String description;
    private boolean isActive;
    private String note;
    private String manufacturerId;
    private String manufacturerPartNo;
    private String manufacturerBarcode;

    private OffsetDateTime createdOn;
    private OffsetDateTime lastModifiedOn;

    private String createdBy;
    private String lastModifiedBy;

    public Part() {
    }

    @Override
    public String toString() {
        return "Part{" +
                "partId=" + partId +
                ", siteId=" + siteId +
                ", costCenterId='" + costCenterId + '\'' +
                ", name='" + name + '\'' +
                ", partNo='" + partNo + '\'' +
                ", createdOn=" + createdOn +
                ", lastModifiedOn=" + lastModifiedOn +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }

    public Part(long partId, long siteId,
                String costCenterId, String name, String partNo,
                OffsetDateTime createdOn, OffsetDateTime lastModifiedOn,
                String createdBy, String lastModifiedBy) {
        this.partId = partId;
        this.siteId = siteId;
        this.costCenterId = costCenterId;
        this.name = name;
        this.partNo = partNo;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getAlternatePartNo() {
        return alternatePartNo;
    }

    public void setAlternatePartNo(String alternatePartNo) {
        this.alternatePartNo = alternatePartNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerPartNo() {
        return manufacturerPartNo;
    }

    public void setManufacturerPartNo(String manufacturerPartNo) {
        this.manufacturerPartNo = manufacturerPartNo;
    }

    public String getManufacturerBarcode() {
        return manufacturerBarcode;
    }

    public void setManufacturerBarcode(String manufacturerBarcode) {
        this.manufacturerBarcode = manufacturerBarcode;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public OffsetDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(OffsetDateTime lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }


}
