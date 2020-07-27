package com.intelliviz.familymanage.model;

import javax.persistence.*;

@Entity
@Table(name="food_product_type")
public class FoodProductType {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    protected FoodProductType() {

    }

    public FoodProductType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
