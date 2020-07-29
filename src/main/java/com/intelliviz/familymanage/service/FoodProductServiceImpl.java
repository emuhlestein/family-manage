package com.intelliviz.familymanage.service;

import com.intelliviz.familymanage.model.FoodProduct;
import com.intelliviz.familymanage.repository.FoodProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodProductServiceImpl implements FoodProductService {

    @Autowired
    FoodProductRepository repo;

    @Override
    public List<FoodProduct> listAllFoodProducts() {
        return repo.getAll();
    }
}
