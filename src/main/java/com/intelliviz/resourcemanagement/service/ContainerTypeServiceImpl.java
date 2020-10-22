package com.intelliviz.resourcemanagement.service;

import com.intelliviz.resourcemanagement.model.ContainerType;
import com.intelliviz.resourcemanagement.repository.ContainerTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerTypeServiceImpl implements ContainerTypeService {

    private final ContainerTypeDao repo;

    @Autowired
    public ContainerTypeServiceImpl(ContainerTypeDao repo) {
        this.repo = repo;
    }

    @Override
    public List<ContainerType> listAll() {
        return repo.getAll();
    }

    @Override
    public ContainerType save(ContainerType containerType) {
        return repo.insert(containerType);
    }

    @Override
    public ContainerType findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public ContainerType findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
