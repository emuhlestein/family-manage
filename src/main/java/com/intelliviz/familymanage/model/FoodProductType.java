package com.intelliviz.familymanage.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="food_product_type")
public class FoodProductType {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "foodProductType")
    private List<FoodProduct> foodProducts = new ArrayList<>();

    protected FoodProductType() {

    }

    public FoodProductType(String name) {
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
