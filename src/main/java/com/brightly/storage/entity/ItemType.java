package com.brightly.storage.entity;

public enum ItemType {
    ITEM, KIT;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
