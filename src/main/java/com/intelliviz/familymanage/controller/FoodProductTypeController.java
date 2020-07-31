package com.intelliviz.familymanage.controller;

import com.intelliviz.familymanage.model.FoodProductType;
import com.intelliviz.familymanage.service.FoodProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodproducttype")
public class FoodProductTypeController {
    @Autowired
    private FoodProductTypeService foodProductTypeService;


    @GetMapping("/")
    public List<FoodProductType> listFoodProducts() {
        return foodProductTypeService.listAll();
    }

    @GetMapping("/{id}")
    public FoodProductType findById(@RequestParam String name) {
        return foodProductTypeService.findByName(name);
    }

    @PostMapping("/")
    public FoodProductType save(@RequestBody FoodProductType insertFoodProductType) {
        System.out.println("name: " + insertFoodProductType.getName());
        if(insertFoodProductType.getName() == null || insertFoodProductType.getName().equals("")) {
            throw new IllegalArgumentException("Name is a required field");
        }

        FoodProductType fpt = foodProductTypeService.findByName(insertFoodProductType.getName());
        if (fpt == null) {
            return foodProductTypeService.save(insertFoodProductType);
        } else {
            if (fpt.getName().toUpperCase().equals(insertFoodProductType.getName().toUpperCase())) {
                throw new IllegalArgumentException("Duplicate name");
            } else {
                return foodProductTypeService.save(insertFoodProductType);
            }
        }
    }
}
