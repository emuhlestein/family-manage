package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductTypeRepositoryTest {

	private static final String TEST_PRODUCT_TYPE1 = "TEST_TYPE1";
	private static final String TEST_PRODUCT_TYPE2 = "TEST_TYPE2";

	@Autowired
    ProductTypeRepository repo;

	@BeforeEach
	public void setup() {
		deleteItem(TEST_PRODUCT_TYPE1);
		deleteItem(TEST_PRODUCT_TYPE2);
		repo.insert(new ProductType(TEST_PRODUCT_TYPE1, ""));
	}

	@AfterEach
	public void cleanup() {
		deleteItem(TEST_PRODUCT_TYPE1);
		deleteItem(TEST_PRODUCT_TYPE2);
	}

	@Test
	@DirtiesContext
	public void testGetById() {
		ProductType fpt1 = repo.findByName(TEST_PRODUCT_TYPE1);
		ProductType fpt2 = repo.findById(fpt1.getId());
		assertEquals(fpt1.getId(), fpt2.getId());
	}

	@Test
	@DirtiesContext
	public void testGetByName() {
		ProductType fpt = repo.findByName(TEST_PRODUCT_TYPE1);
		assertEquals(TEST_PRODUCT_TYPE1, fpt.getName());
	}

	@Test
	@DirtiesContext
	public void testAddProductType() {
		ProductType fpt1 = repo.insert(new ProductType(TEST_PRODUCT_TYPE2, ""));
		ProductType fpt2 = repo.findById(fpt1.getId());
		assertEquals(fpt1.getId(), fpt2.getId());
		repo.deleteById(fpt1.getId());
	}

	@Test
	@DirtiesContext
	public void testAddDuplicateProductType() {
		ProductType fpt = new ProductType(TEST_PRODUCT_TYPE1, "");
		try {
			final List<ProductType> productTypes = repo.getAll();
			repo.insert(fpt);
		} catch(DataIntegrityViolationException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getCause());

			// JDBC wraps ConstraintViolationException; can't catch it directly
			Throwable t = e.getCause();
			while((t != null) && !(t instanceof SQLIntegrityConstraintViolationException)) {
				t = t.getCause();
			}

			assertTrue(t instanceof SQLIntegrityConstraintViolationException);
		}
	}

	private void deleteItem(String itemToDelete) {
		List<ProductType> list = repo.getAll();
		List<ProductType> fptList = list.stream()
				.filter(item -> item.getName().equals(itemToDelete))
				.collect(Collectors.toList());
		if(fptList.size() > 0) {
			repo.deleteById(fptList.get(0).getId());
		}
	}
}
