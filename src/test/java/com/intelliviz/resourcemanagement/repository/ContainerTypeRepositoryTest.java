package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ContainerType;
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
public class ContainerTypeRepositoryTest {

	private static final String TEST_CONTAINER_TYPE1 = "TEST_TYPE1";
	private static final String TEST_CONTAINER_TYPE2 = "TEST_TYPE2";

	@Autowired
	ContainerTypeDao repo;

	@BeforeEach
	public void setup() {
		deleteItem(TEST_CONTAINER_TYPE1);
		deleteItem(TEST_CONTAINER_TYPE2);
		repo.insert(new ContainerType(TEST_CONTAINER_TYPE1, ""));
	}

	@AfterEach
	public void cleanup() {
		deleteItem(TEST_CONTAINER_TYPE1);
		deleteItem(TEST_CONTAINER_TYPE2);
	}

	@Test
	@DirtiesContext
	public void testGetById() {
		ContainerType fpt1 = repo.findByName(TEST_CONTAINER_TYPE1);
		ContainerType fpt2 = repo.findById(fpt1.getId());
		assertEquals(fpt1.getId(), fpt2.getId());
	}

	@Test
	@DirtiesContext
	public void testGetByName() {
		ContainerType fpt = repo.findByName(TEST_CONTAINER_TYPE1);
		assertEquals(TEST_CONTAINER_TYPE1, fpt.getName());
	}

	@Test
	@DirtiesContext
	public void testAddContainerType() {
		ContainerType fpt1 = repo.insert(new ContainerType(TEST_CONTAINER_TYPE2, ""));
		ContainerType fpt2 = repo.findById(fpt1.getId());
		assertEquals(fpt1.getId(), fpt2.getId());
		repo.deleteById(fpt1.getId());
	}

	@Test
	@DirtiesContext
	public void testAddDuplicateContainerType() {
		ContainerType fpt = new ContainerType(TEST_CONTAINER_TYPE1, "");
		try {
			final List<ContainerType> containerTypes = repo.getAll();
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
		List<ContainerType> list = repo.getAll();
		List<ContainerType> fptList = list.stream()
				.filter(item -> item.getName().equals(itemToDelete))
				.collect(Collectors.toList());
		if(fptList.size() > 0) {
			repo.deleteById(fptList.get(0).getId());
		}
	}
}
