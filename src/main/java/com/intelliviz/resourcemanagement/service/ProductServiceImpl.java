package com.intelliviz.resourcemanagement.service;

import com.intelliviz.resourcemanagement.model.Product;
import com.intelliviz.resourcemanagement.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao repo;

    @Override
    public List<Product> getAll() {
        return repo.getAll();
    }
}
