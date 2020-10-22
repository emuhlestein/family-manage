package com.intelliviz.resourcemanagement.controller;

import com.intelliviz.resourcemanagement.model.ContainerType;
import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.service.ContainerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/containertype")
public class ContainerTypeController {
    @Autowired
    private ContainerTypeService service;

    @GetMapping("")
    public List<ContainerType> listAllContainerTypes() {
        return service.listAll();
    }

    @GetMapping("{id}")
    public ContainerType findById(@PathVariable int id) {
        return service.findById((long)id);
    }

    @PostMapping("")
    public ContainerType save(@RequestBody ContainerType insertContainerType) {
        System.out.println("name: " + insertContainerType.getName());
        if(insertContainerType.getName() == null || insertContainerType.getName().equals("")) {
            throw new IllegalArgumentException("Name is a required field");
        }

        ContainerType fpt = service.findByName(insertContainerType.getName());
        if (fpt == null) {
            return service.save(insertContainerType);
        } else {
            if (fpt.getName().toUpperCase().equals(insertContainerType.getName().toUpperCase())) {
                throw new IllegalArgumentException("Duplicate name");
            } else {
                return service.save(insertContainerType);
            }
        }
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById((long)id);
    }
}
