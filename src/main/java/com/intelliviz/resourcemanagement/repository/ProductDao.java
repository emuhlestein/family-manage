package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.Product;
import com.intelliviz.resourcemanagement.model.ProductType;
import java.util.List;

public interface ProductDao {
    Product findById(Long id);
    Product findByName(String name);
    List<Product> getAll();
    ProductType save(ProductType productType);
    void deleteById(Long id);
}
