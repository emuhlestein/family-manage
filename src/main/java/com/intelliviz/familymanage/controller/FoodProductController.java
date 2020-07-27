package com.intelliviz.familymanage.controller;

import com.intelliviz.familymanage.model.FoodProduct;
import com.intelliviz.familymanage.service.FoodProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodProductController {
    @Autowired
    private FoodProductService foodProductService;

    @RequestMapping("/foodproduct")
    public List<FoodProduct> listFoodProducts() {
        return foodProductService.listAllFoodItems();
    }
}
