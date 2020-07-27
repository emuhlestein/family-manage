package com.intelliviz.familymanage.controller;

import com.intelliviz.familymanage.model.FoodProduct;
import com.intelliviz.familymanage.service.FoodStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodStorageController {
    @Autowired
    private FoodStorageService foodStorageService;

    @RequestMapping("/foodproduct")
    public List<FoodProduct> listFoodProducts() {
        return foodStorageService.listAllFoodItems();
    }
}
