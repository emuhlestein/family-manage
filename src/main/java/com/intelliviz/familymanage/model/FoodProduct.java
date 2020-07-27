package com.intelliviz.familymanage.model;

import javax.persistence.*;

@Entity
@Table(name = FoodProduct.TABLE_NAME)
public class FoodProduct {
    public static final String TABLE_NAME= "food_product";

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;
    private float weight;

    @Column(name="num_cans")
    private int numCans;

    @Column(name="num_boxes")
    private int numBoxes;

    protected FoodProduct() {
    }

    public FoodProduct(String name, String type, float weight, int numCans, int numBoxes) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.numCans = numCans;
        this.numBoxes = numBoxes;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getNumBoxes() {
        return numBoxes;
    }

    public void setNumBoxes(int numBoxes) {
        this.numBoxes = numBoxes;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", numCans=" + numCans +
                ", numBoxes=" + numBoxes +
                '}';
    }
}
