package com.intelliviz.resourcemanagement.service;

import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.repository.ProductTypeRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductTypeServiceImplTest.class)
class ProductTypeServiceImplTest {

    @Mock
    private ProductTypeRepositoryImpl productTypeDao;

    @InjectMocks
    private ProductTypeServiceImpl service;

    @Test
    void listAll() {
        when(productTypeDao.getAll()).thenReturn(
                new ArrayList<>(
                        Arrays.asList(
                                new ProductType(1L, "Grain", "test"),
                                new ProductType(2L, "Beans", "test"),
                                new ProductType(3L, "Sugar", "test")
                        )));

        final List<ProductType> productTypes = service.listAll();

        assertEquals(3, productTypes.size());
        assertEquals("Grain", productTypes.get(0).getName());
        assertEquals("Beans", productTypes.get(1).getName());
        assertEquals("Sugar", productTypes.get(2).getName());
    }

    @Test
    void save() {
        ProductType productType = new ProductType(1L, "Grain", "test");

        when(productTypeDao.insert(any(ProductType.class))).thenReturn(productType);

        final ProductType savedProductType = service.save(productType);
        assertEquals("Grain", productType.getName());
        assertEquals(1L, productType.getId());
    }

    @Test
    void findByName() {
        when(productTypeDao.findByName(anyString())).thenReturn(new ProductType(1L, "Grain", "test"));

        final ProductType productType = service.findByName("Grain");

        assertEquals("Grain", productType.getName());
    }

    @Test
    void findById() {
        when(productTypeDao.findById(anyLong())).thenReturn(new ProductType(1L, "Grain", "test"));

        final ProductType productType = service.findById(1L);

        assertEquals(1L, productType.getId());
    }

    @Test
    void deleteById() {
    }
}