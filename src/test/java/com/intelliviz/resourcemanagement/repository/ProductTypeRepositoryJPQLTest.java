package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTypeRepositoryJPQLTest {

	private static final String TEST_TYPE = "TEST_TYPE";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	EntityManager em;

	@Test
	public void testGetAll() {
//		List list = em.createQuery("Select f From FoodProductType f").getResultList();
//		logger.info("Select f From FoodProductType f -> {}", list);
	}

	@Test
	public void testGetAllTyped() {
//		TypedQuery<FoodProductType> query2 = em.createQuery("Select f From FoodProductType f where id = ''", FoodProductType.class);
//		TypedQuery<ProductType> query = em.createQuery("Select f From FoodProductType f", ProductType.class);
//		List<ProductType> list = query.getResultList();
//		logger.info("Select f From FoodProductType f -> {}", list);
	}

	@Test
	public void testGetAllTypedWithWhere() {
//		TypedQuery<ProductType> query = em.createQuery("Select f From FoodProductType f where id = '1'", ProductType.class);
//		List<ProductType> list = query.getResultList();
//		logger.info("Select f From FoodProductType f -> {}", list);
	}
}
