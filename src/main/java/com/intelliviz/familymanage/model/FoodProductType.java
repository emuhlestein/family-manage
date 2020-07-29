package com.intelliviz.familymanage.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = FoodProductType.TABLE_NAME)
public class FoodProductType {
    public static final String TABLE_NAME= "food_product_type";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<FoodProduct> foodProducts = new ArrayList<>();

    protected FoodProductType() {

    }

    public FoodProductType(String name) {
        this.name = name;
    }
    public FoodProductType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<FoodProduct> getFoodProducts() {
        return foodProducts;
    }

    public void addFoodProduct(FoodProduct foodProduct) {
        foodProducts.add(foodProduct);
    }

    public void removeFoodProduct(FoodProduct foodProduct) {
        foodProducts.remove(foodProduct);
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
