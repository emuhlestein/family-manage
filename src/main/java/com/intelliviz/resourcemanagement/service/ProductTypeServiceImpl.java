package com.intelliviz.resourcemanagement.service;

import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    @Qualifier("ProductTypeJpaRepository")
    private ProductTypeRepository repo;

    @Override
    public List<ProductType> listAll() {
        return repo.getAll();
    }

    @Override
    public ProductType save(ProductType insertProductType) {
        return repo.insert(insertProductType);
    }

    @Override
    public ProductType findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public ProductType findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Long deleteById(Long id) {
        return repo.deleteById(id);
    }
}
