package com.intelliviz.familymanage.repository;

import com.intelliviz.familymanage.model.FoodProductType;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodProductTypeRepositoryTest {

	private static final String TEST_FOOD_PRODUCT_TYPE1 = "TEST_TYPE1";
	private static final String TEST_FOOD_PRODUCT_TYPE2 = "TEST_TYPE2";

	@Autowired
	FoodProductTypeRepository repo;

	@Before
	public void setup() {
		deleteItem(TEST_FOOD_PRODUCT_TYPE1);
		deleteItem(TEST_FOOD_PRODUCT_TYPE2);
		repo.save(new FoodProductType(TEST_FOOD_PRODUCT_TYPE1));
	}

	@After
	public void cleanup() {
		deleteItem(TEST_FOOD_PRODUCT_TYPE1);
		deleteItem(TEST_FOOD_PRODUCT_TYPE2);
	}

	@Test
	@DirtiesContext
	public void testGetById() {
		FoodProductType fpt1 = repo.findByName(TEST_FOOD_PRODUCT_TYPE1);
		FoodProductType fpt2 = repo.findById(fpt1.getId());
		assertEquals(fpt1.getId(), fpt2.getId());
	}

	@Test
	@DirtiesContext
	public void testGetByName() {
		FoodProductType fpt = repo.findByName(TEST_FOOD_PRODUCT_TYPE1);
		assertEquals(TEST_FOOD_PRODUCT_TYPE1, fpt.getName());
	}

	@Test
	@DirtiesContext
	public void testAddProductType() {
		FoodProductType fpt1 = repo.save(new FoodProductType(TEST_FOOD_PRODUCT_TYPE2));
		FoodProductType fpt2 = repo.findById(fpt1.getId());
		assertEquals(fpt1.getId(), fpt2.getId());
		repo.deleteById(fpt1.getId());
	}

	@Test
	@DirtiesContext
	public void testAddDuplicateProductType() {
		FoodProductType fpt = new FoodProductType(TEST_FOOD_PRODUCT_TYPE1);
		try {
			repo.save(fpt);
		} catch(DataIntegrityViolationException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getCause());

			// JPA wraps ConstraintViolationException; can't catch it directly
			Throwable t = e.getCause();
			while((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}

			assertTrue(t instanceof ConstraintViolationException);
		}
	}

	private void deleteItem(String itemToDelete) {
		List<FoodProductType> list = repo.getAll();
		List<FoodProductType> fptList = list.stream()
				.filter(item -> item.getName().equals(itemToDelete))
				.collect(Collectors.toList());
		if(fptList.size() > 0) {
			repo.deleteById(fptList.get(0).getId());
		}
	}
}
