package com.revature.services;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.PersonCollections;
import com.revature.data.PersonDAO;
import com.revature.exceptions.CatAlreadyAdoptedException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class CatServiceTest {
	private static CatService catServ;
	private static PersonDAO personDao;
	private Cat cat;
	
	@Order(1)
	@Test
	public void testGetAvailableCats() {
		Set<Cat> availableCats = catServ.getAvailableCats();
		
		for (Cat cat : availableCats) {
			Status s = cat.getStatus();
			assertEquals("Available", s.getName());
			this.cat = cat;
		}
	}
	
	@Order(2)
	@Test
	public void testAdoptAvailableCat() {
		Person p = personDao.getById(1);
		try {
			catServ.adoptCat(p, catServ.getCatById(1));
		} catch (CatAlreadyAdoptedException e) {
			e.printStackTrace();
		}
		
		Person testPerson = personDao.getById(1);
		Cat testCat = catServ.getCatById(1);
		
		assertTrue(testPerson.getCats().contains(testCat));
		assertTrue(("Adopted").equals(testCat.getStatus().getName()));
	}
	
	@Order(3)
	@Test
	public void testAdoptAdoptedCat() {
		Person p = personDao.getById(1);
		Cat testCat = catServ.getCatById(3);
		
		boolean personHasCat = p.getCats().contains(testCat);

		assertThrows(CatAlreadyAdoptedException.class, () -> {
			catServ.adoptCat(p, catServ.getCatById(3));
		});
		
		Person testPerson = personDao.getById(1);
		
		if (!personHasCat)
			assertFalse(testPerson.getCats().contains(testCat));
	}
	
	@BeforeAll
	public static void setup() {
		catServ = new CatServiceImpl();
		personDao = new PersonCollections();
		System.out.println("This will happen once before any of the tests");
	}
	
	@BeforeEach
	public void beforeEachTest() {
		System.out.println("This will happen before each test");
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("This will happen after each test");
	}
	
	@AfterAll
	public static void tearDown() {
		System.out.println("This will happen once after all of the tests");
	}
}
