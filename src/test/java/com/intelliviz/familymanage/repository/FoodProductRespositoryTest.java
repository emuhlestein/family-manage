package com.intelliviz.familymanage.repository;

import com.intelliviz.familymanage.model.FoodProduct;
import com.intelliviz.familymanage.model.FoodProductType;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodProductRespositoryTest {

	private static final String TEST_FOOD_PRODUCT_TYPE1 = "TEST_TYPE1";
	private static final String TEST_FOOD_PRODUCT1 = "TEST_FOOD_PRODUCT1";

	@Autowired
	FoodProductRepository repo;

	@Autowired
	FoodProductTypeRepository typeRepo;

	@Autowired
	EntityManager em;

//	@Test
//	@DirtiesContext
//	public void testGetById() {
//		FoodProductType fpt1 = repo.save(new FoodProductType(TEST_TYPE));
//		FoodProductType fpt2 = repo.findById(fpt1.getId());
//		assertEquals(fpt1.getId(), fpt2.getId());
//	}
//
//	@Test
//	@DirtiesContext
//	public void testGetByName() {
//		FoodProductType fpt1 = repo.save(new FoodProductType(TEST_TYPE));
//		FoodProductType fpt2 = repo.findByName(TEST_TYPE);
//		assertEquals(fpt1.getName(), fpt2.getName());
//	}

	@Test
	@DirtiesContext
	public void testAddProduct() {
		FoodProductType fpt = new FoodProductType(TEST_FOOD_PRODUCT_TYPE1);
		fpt = typeRepo.save(fpt);

//		mock(SomeDataService.class);

//		FoodProduct fp = new FoodProduct(TEST_FOOD_PRODUCT1, 8, 30);
//		fp.setFoodProductType(fpt);
//		fp = repo.save(fp);
//		FoodProductType fpt2 = repo.findById(fpt1.getId());
//		assertEquals(fpt1.getId(), fpt2.getId());
	}

//	@Test
//	@DirtiesContext
//	public void testAddDuplicateProductType() {
//		FoodProductType fpt = new FoodProductType(TEST_TYPE);
//		repo.save(fpt);
//		fpt = new FoodProductType(TEST_TYPE);
//		try {
//			repo.save(fpt);
//		} catch(DataIntegrityViolationException e) {
//			System.out.println(e.getLocalizedMessage());
//			System.out.println(e.getCause());
//
//			// JPA wraps ConstraintViolationException; can't catch it directly
//			Throwable t = e.getCause();
//			while((t != null) && !(t instanceof ConstraintViolationException)) {
//				t = t.getCause();
//			}
//
//			assertTrue(t instanceof ConstraintViolationException);
//		}
//	}
}
