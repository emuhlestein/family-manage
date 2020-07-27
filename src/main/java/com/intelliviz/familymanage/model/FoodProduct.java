package com.intelliviz.familymanage.model;

import javax.persistence.*;

@Entity
@Table(name = FoodProduct.TABLE_NAME)
public class FoodProduct {
    public static final String TABLE_NAME= "food_product";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name="num_cans", nullable = false)
    private int numCans;

    @ManyToOne
    @JoinColumn(name="food_product_type", nullable = false)
    private FoodProductType foodProductType;

    protected FoodProduct() {
    }

    public FoodProduct(String name, FoodProductType foodProductType, float weight, int numCans, int numBoxes) {
        this.name = name;
        this.weight = weight;
        this.numCans = numCans;
        this.foodProductType = foodProductType;
    }

    public FoodProductType getFoodProductType() {
        return foodProductType;
    }

    public void setFoodProductType(FoodProductType foodProductType) {
        this.foodProductType = foodProductType;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getNumCans() {
        return numCans;
    }

    public void setNumCans(int numCans) {
        this.numCans = numCans;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", type=" + foodProductType.getName() +
                ", numCans=" + numCans +
                '}';
    }
}
