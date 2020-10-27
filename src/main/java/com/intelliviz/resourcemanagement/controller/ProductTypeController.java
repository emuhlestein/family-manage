package com.intelliviz.resourcemanagement.controller;

import com.intelliviz.resourcemanagement.exception.MissingNameException;
import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/producttype")
public class ProductTypeController {
    @Autowired
    private ProductTypeService service;

    @GetMapping("")
    public List<ProductType> listAllProductTypes() {
        return service.listAll();
    }

    @GetMapping("{id}")
    public ProductType findById(@PathVariable int id) {
        final ProductType productType = service.findById((long) id);
        return productType;
    }

    @PostMapping("")
    public ResponseEntity<ProductType> save(@RequestBody ProductType insertProductType) throws MissingNameException {
        System.out.println("name: " + insertProductType.getName());
        if(insertProductType.getName() == null || insertProductType.getName().equals("")) {
            throw new MissingNameException("Name is a required field");
        }

        ProductType fpt = service.findByName(insertProductType.getName());
        final ProductType savedProducType = service.save(insertProductType);

        // so returning status is 201 - REST best practice
        // the location is the path of the new resource. This is sent back in the header.
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProducType.getId()).toUri();
        if (fpt == null) {
            return ResponseEntity.created(location).build();
        } else {
            if (fpt.getName().toUpperCase().equals(insertProductType.getName().toUpperCase())) {
                throw new IllegalArgumentException("Duplicate name");
            } else {
                return ResponseEntity.created(location).build();
            }
        }
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById((long)id);
    }
}
