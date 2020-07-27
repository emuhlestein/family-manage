package com.intelliviz.familymanage.service;

import com.intelliviz.familymanage.model.FoodProduct;
import com.intelliviz.familymanage.model.FoodProductType;

import java.util.List;

public interface FoodProductTypeService {
    List<FoodProductType> listAll();
    FoodProductType save(FoodProductType foodProductType);
    FoodProductType findByName(String name);
}
