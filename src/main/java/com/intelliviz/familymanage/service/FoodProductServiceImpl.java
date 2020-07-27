package com.intelliviz.familymanage.service;

import com.intelliviz.familymanage.model.FoodProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodProductServiceImpl implements FoodProductService {

    @Autowired
    FoodStorageRepository repo;

    @Override
    public List<FoodProduct> listAllFoodProducts() {
        return repo.getAllFoodProducts();
//        return Arrays.asList("Red Wheat", "White Wheat", "White Rice", "Black Beans", "Pinto Beans");
//        return List.of("Red Wheat", "White Wheat", "White Rice", "Black Beans", "Pinto Beans");
    }
}