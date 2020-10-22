package com.intelliviz.resourcemanagement.service;

import com.intelliviz.resourcemanagement.controller.ContainerTypeController;
import com.intelliviz.resourcemanagement.model.ContainerType;

import java.util.List;

public interface ContainerTypeService {
    List<ContainerType> listAll();
    ContainerType save(ContainerType containerType);
    ContainerType findByName(String name);
    ContainerType findById(Long id);
    void deleteById(Long id);
}
