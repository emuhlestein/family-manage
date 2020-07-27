package com.intelliviz.familymanage.service;

import com.intelliviz.familymanage.model.FoodProductType;
import com.intelliviz.familymanage.repository.FoodProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodProductTypeServiceImpl implements FoodProductTypeService {
    @Autowired
    FoodProductTypeRepository repo;

    @Override
    public List<FoodProductType> listAll() {
        return repo.getAll();
    }

    @Override
    public FoodProductType save(FoodProductType insertFoodProductType) {
        System.out.println(insertFoodProductType.getName());
        return repo.save(insertFoodProductType);
    }

    @Override
    public FoodProductType findByName(String name) {
        return repo.findByName(name);
    }
}
