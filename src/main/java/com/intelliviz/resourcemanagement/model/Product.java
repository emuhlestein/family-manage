package com.intelliviz.resourcemanagement.model;

public class Product {
    private long id;
    private String name; // White Wheat
    private String description;
    private long product_type_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("\nProduct [id=%s, name=%s, description=%s]", id, name, description);
    }
}
