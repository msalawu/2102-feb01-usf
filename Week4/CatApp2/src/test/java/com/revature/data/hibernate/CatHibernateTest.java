package com.revature.data.hibernate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import com.revature.beans.Cat;
import com.revature.data.DAOFactory;

@TestMethodOrder(OrderAnnotation.class)
public class CatHibernateTest {
	public static CatHibernate catDao;
	public static Cat testCat;
	
	@BeforeAll
	public static void setup() {
		catDao = new CatHibernate();
		testCat = new Cat();
		testCat.setBreed(DAOFactory.getBreedDAO().getById(1));
		testCat.setStatus(DAOFactory.getStatusDAO().getById(1));
	}
	
	@Test
	public void getCatByIdIsNotNull() {
		Cat c = catDao.getById(1);
		assertNotNull(c);
	}
	
	@Test
	public void getAvailableCatsAreAvailable() {
		Set<Cat> cats = catDao.getAvailableCats();
		for (Cat c : cats) {
			assertEquals("Available", c.getStatus().getName());
		}
	}
	
	@Test
	public void getAllCatsIsNotEmpty() {
		Set<Cat> cats = catDao.getAll();
		assertFalse(cats.isEmpty());
	}
	
	@Test
	@Order(1)
	public void addCatAssignsId() {
		Integer id = testCat.getId();
		testCat = catDao.add(testCat);
		assertNotEquals(id, testCat.getId());
	}
	
	@Test
	@Order(2)
	public void updateCatUpdatesCat() {
		String name = testCat.getName();
		testCat.setName("Test");
		catDao.update(testCat);
		testCat = catDao.getById(testCat.getId());
		assertNotEquals(name, testCat.getName());
	}
	
	@Test
	@Order(3)
	public void deleteCatDeletesCat() {
		catDao.delete(testCat);
		assertNull(catDao.getById(testCat.getId()));
	}
}
