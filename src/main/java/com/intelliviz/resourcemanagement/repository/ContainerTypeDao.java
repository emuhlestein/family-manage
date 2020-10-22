package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ContainerType;

import java.util.List;

public interface ContainerTypeDao {
    List<ContainerType> getAll();
    ContainerType findByName(String name);
    ContainerType findById(long id);
    ContainerType insert(ContainerType containerType);
    void deleteById(long id);
}
