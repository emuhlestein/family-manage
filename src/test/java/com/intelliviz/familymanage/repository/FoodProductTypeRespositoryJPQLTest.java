package com.intelliviz.familymanage.repository;

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
import javax.persistence.TypedQuery;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodProductTypeRespositoryJPQLTest {

	private static final String TEST_TYPE = "TEST_TYPE";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void testGetAll() {
		List list = em.createQuery("Select f From FoodProductType f").getResultList();
		logger.info("Select f From FoodProductType f -> {}", list);
	}

	@Test
	public void testGetAllTyped() {
//		TypedQuery<FoodProductType> query2 = em.createQuery("Select f From FoodProductType f where id = ''", FoodProductType.class);
		TypedQuery<FoodProductType> query = em.createQuery("Select f From FoodProductType f", FoodProductType.class);
		List<FoodProductType> list = query.getResultList();
		logger.info("Select f From FoodProductType f -> {}", list);
	}

	@Test
	public void testGetAllTypedWithWhere() {
		TypedQuery<FoodProductType> query = em.createQuery("Select f From FoodProductType f where id = '1'", FoodProductType.class);
		List<FoodProductType> list = query.getResultList();
		logger.info("Select f From FoodProductType f -> {}", list);
	}
}
