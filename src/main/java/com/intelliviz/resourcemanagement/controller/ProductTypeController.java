package com.intelliviz.resourcemanagement.controller;

import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.service.ProductTypeService;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producttype")
public class ProductTypeController {
    @Autowired
    private ProductTypeService service;


    @GetMapping("")
    public List<ProductType> listFoodProducts() {
        return service.listAll();
    }

    @GetMapping("{id}")
    public ProductType findById(@PathVariable int id) {
        return service.findById((long)id);
    }

    @PostMapping("")
    public ProductType save(@RequestBody ProductType insertProductType) {
        System.out.println("name: " + insertProductType.getName());
        if(insertProductType.getName() == null || insertProductType.getName().equals("")) {
            throw new IllegalArgumentException("Name is a required field");
        }

        ProductType fpt = service.findByName(insertProductType.getName());
        if (fpt == null) {
            //TODO return service.save(insertProductType);
        } else {
            if (fpt.getName().toUpperCase().equals(insertProductType.getName().toUpperCase())) {
                throw new IllegalArgumentException("Duplicate name");
            } else {
                // TODO return service.save(insertProductType);
            }
        }
        return null;
    }
}
