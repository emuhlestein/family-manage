package com.intelliviz.resourcemanagement.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    @Size(min=2, max=64)
    @ApiModelProperty(notes="name should have at least 2 characters and no more than 64")
    private String name;

    @Column(name = "description")
    @Size(max=256)
    @ApiModelProperty(notes="description can have at most 256 characters")
    private String description;

    public ProductType() {
    }

    public ProductType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductType(long id, String name, String description) {
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
        return String.format("\nProductType [id=%s, name=%s, description=%s]", id, name, description);
    }
}
