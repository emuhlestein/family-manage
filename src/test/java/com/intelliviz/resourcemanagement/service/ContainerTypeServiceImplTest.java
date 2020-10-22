package com.intelliviz.resourcemanagement.service;

import com.intelliviz.resourcemanagement.model.ContainerType;
import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.repository.ContainerTypeDaoImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ContainerTypeServiceImplTest.class)
class ContainerTypeServiceImplTest {

    @Mock
    private ContainerTypeDaoImpl containerTypeDao;

    @InjectMocks
    private ContainerTypeServiceImpl service;

    @Test
    void listAll() {
        when(containerTypeDao.getAll()).thenReturn(
                new ArrayList<>(
                        Arrays.asList(
                                new ContainerType(1L, "#10 Can", "test"),
                                new ContainerType(2L, "#5 Can", "test"),
                                new ContainerType(3L, "5 gallon Bucket", "test")
                        )));

        final List<ContainerType> containerTypes = service.listAll();

        assertEquals(3, containerTypes.size());
        assertEquals("#10 Can", containerTypes.get(0).getName());
        assertEquals("#5 Can", containerTypes.get(1).getName());
        assertEquals("5 gallon Bucket", containerTypes.get(2).getName());
    }

    @Test
    void save() {
        ContainerType containerType = new ContainerType(1L, "#10 Can", "test");

        when(containerTypeDao.insert(any(ContainerType.class))).thenReturn(containerType);

        final ContainerType savedProductType = service.save(containerType);
        assertEquals("#10 Can", containerType.getName());
        assertEquals(1L, containerType.getId());
    }

    @Test
    void findByName() {
        when(containerTypeDao.findByName(anyString())).thenReturn(new ContainerType(1L, "#10 Can", "test"));

        final ContainerType containerType = service.findByName("#10 Can");

        assertEquals("#10 Can", containerType.getName());
    }

    @Test
    void findById() {
        when(containerTypeDao.findById(anyLong())).thenReturn(new ContainerType(1L, "#10 Can", "test"));

        final ContainerType containerType = service.findById(1L);

        assertEquals(1L, containerType.getId());
    }

    @Test
    void deleteById() {
    }
}