package com.intelliviz.resourcemanagement.controller;

import com.intelliviz.resourcemanagement.model.Product;
import com.intelliviz.resourcemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/foodproduct")
    public List<Product> listFoodProducts() {
        return productService.getAll();
    }
}
