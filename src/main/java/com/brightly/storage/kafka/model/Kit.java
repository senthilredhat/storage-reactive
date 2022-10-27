package com.brightly.storage.kafka.model;

import java.sql.Timestamp;

public class Kit {
    private long kitId;
    private String name;
    private String note;
    private String kitNo;
    private Timestamp createdOn;
    private Timestamp lastModifiedOn;
    private String createdBy;
    private String lastModifiedBy;

    public Kit() {
    }

    public Kit(long kitId, String name, String note, String kitNo,
               Timestamp createdOn, Timestamp lastModifiedOn,
               String createdBy, String lastModifiedBy) {
        this.kitId = kitId;
        this.name = name;
        this.note = note;
        this.kitNo = kitNo;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public long getKitId() {
        return kitId;
    }

    public void setKitId(long kitId) {
        this.kitId = kitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKitNo() {
        return kitNo;
    }

    public void setKitNo(String kitNo) {
        this.kitNo = kitNo;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Timestamp lastModifiedOn) {
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

    @Override
    public String toString() {
        return "Kit{" +
                "kitId=" + kitId +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", kitNo='" + kitNo + '\'' +
                ", createdOn=" + createdOn +
                ", lastModifiedOn=" + lastModifiedOn +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }
}
