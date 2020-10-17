package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.Product;
import com.intelliviz.resourcemanagement.model.ProductType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{
    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public ProductType save(ProductType productType) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
