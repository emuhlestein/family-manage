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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_product_type_id", nullable = false)
    private FoodProductType type;

    protected FoodProduct() {
    }

    public FoodProduct(String name, float weight, int numCans) {
        this.name = name;
        this.weight = weight;
        this.numCans = numCans;
    }

    public FoodProductType getFoodProductType() {
        return type;
    }

    public void setFoodProductType(FoodProductType foodProductType) {
        this.type = foodProductType;
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
                ", type=" + type.getName() +
                ", numCans=" + numCans +
                '}';
    }
}
