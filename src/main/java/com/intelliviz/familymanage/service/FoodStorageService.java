package com.intelliviz.familymanage.service;

import com.intelliviz.familymanage.model.FoodProduct;

import java.util.List;

public interface FoodStorageService {
    List<FoodProduct> listAllFoodItems();
}
