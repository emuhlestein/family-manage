package com.intelliviz.resourcemanagement.model;

public class ContainerType {
    private long id;
    private String name;
    private String description;

    public ContainerType() {
    }

    public ContainerType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ContainerType(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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
        return String.format("\nContainerType [id=%s, name=%s, description=%s]", id, name, description);
    }
}
