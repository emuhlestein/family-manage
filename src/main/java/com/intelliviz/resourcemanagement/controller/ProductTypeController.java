package com.intelliviz.resourcemanagement.controller;

import com.intelliviz.resourcemanagement.exception.DuplicateNameException;
import com.intelliviz.resourcemanagement.exception.MissingNameException;
import com.intelliviz.resourcemanagement.exception.ProductTypeNotFoundException;
import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ProductType findById(@PathVariable int id) throws ProductTypeNotFoundException {
        final ProductType productType = service.findById((long) id);
        if(productType == null) {
            throw new ProductTypeNotFoundException("id-" + id);
        }
        return productType;
    }

    @PostMapping("")
    public ResponseEntity<ProductType> save(@Valid @RequestBody ProductType insertProductType) throws MissingNameException, DuplicateNameException {
        System.out.println("name: " + insertProductType.getName());
        if(insertProductType.getName() == null || insertProductType.getName().equals("")) {
            throw new MissingNameException("Name is a required field");
        }

        ProductType fpt = service.findByName(insertProductType.getName());
        final ProductType savedProductType = service.save(insertProductType);

        // so returning status is 201 - REST best practice
        // the location is the path of the new resource. This is sent back in the header.
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProductType.getId()).toUri();
        if (fpt == null) {
            return ResponseEntity.created(location).build();
        } else {
            if (fpt.getName().toUpperCase().equals(insertProductType.getName().toUpperCase())) {
                throw new DuplicateNameException("Duplicate name");
            } else {
                return ResponseEntity.created(location).build();
            }
        }
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) throws ProductTypeNotFoundException {
        Long deletedId = service.deleteById((long)id);
        if(deletedId == null) {
            throw new ProductTypeNotFoundException("id-" + id);
        }
    }
}
