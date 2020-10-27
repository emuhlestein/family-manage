package com.intelliviz.resourcemanagement.repository;


import com.intelliviz.resourcemanagement.model.ProductType;

import java.util.List;

public interface ProductTypeRepository {
    List<ProductType> getAll();
    ProductType findByName(String name);
    ProductType findById(long id);
    ProductType insert(ProductType productType);
    Long deleteById(long id);
}
