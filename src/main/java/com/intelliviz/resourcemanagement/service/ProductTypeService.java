package com.intelliviz.resourcemanagement.service;

import com.intelliviz.resourcemanagement.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> listAll();
    ProductType save(ProductType productType);
    ProductType findByName(String name);
    ProductType findById(Long id);
}
