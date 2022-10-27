package com.brightly.storage.kafka.model;

public class PartKit {
    private long kitId;
    private long partId;
    private long partKitId;
    private long quantity;

    public PartKit() {
    }

    public PartKit(long kitId, long partId, long partKitId, long quantity) {
        this.kitId = kitId;
        this.partId = partId;
        this.partKitId = partKitId;
        this.quantity = quantity;
    }

    public long getKitId() {
        return kitId;
    }

    public void setKitId(long kitId) {
        this.kitId = kitId;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public long getPartKitId() {
        return partKitId;
    }

    public void setPartKitId(long partKitId) {
        this.partKitId = partKitId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PartKit{" +
                "kitId=" + kitId +
                ", partId=" + partId +
                ", partKitId=" + partKitId +
                ", quantity=" + quantity +
                '}';
    }
}
