package com.revature.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.data.CatCollections;
import com.revature.data.CatDAO;
import com.revature.data.PersonCollections;
import com.revature.data.PersonDAO;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(Alphanumeric.class)
//@TestMethodOrder(OrderAnnotation.class)
public class ExampleTest {
	
	// this method will run ONCE before any of your tests are run
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("This will happen before any of the tests");
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
	public static void afterAllTests() {
		System.out.println("This will happen once after all of the tests");
	}
	
	// Assertion methods
	// assertEquals
	// assertTrue
	// assertFalse
	// assertArrayEquals
	// assertThrows
	
	//@DisplayName("Cat Name test")
	//@Order(1)
	@Test
	public void testTheThing() {
		Cat c = new Cat();
		c.setName("Fluffy");
		assertEquals("Fluffy", c.getName());
	}
	
	//@DisplayName("Testing i less than 100")
	//@Order(2)
	@Test
	public void testTheOtherThing() {
		int i = 120;
		assertTrue(i < 100);
	}
	
	//@DisplayName("Testing a NullPointerException")
	//@Order(3)
	@Test
	public void testException() {
		Cat c = null;
		
		// assertThrows takes the type of the exception
		// that you are expecting, and Executable is a
		// functional interface so we write a lambda for it
		// that includes our code in which we expect an
		// exception to occur
		assertThrows(NullPointerException.class, () -> {
			c.getName();
		});
	}
	
	@Test
	public void testCatCollectionUpdate() {
		CatDAO catDao = new CatCollections();
		Cat c = catDao.getById(1);
		c.setName("Cat");
		catDao.update(c);
		assertEquals(c, catDao.getById(c.getId()));
		System.out.println(catDao.getById(c.getId()));
	}
	
	@Test
	public void testPersonCollectionUpdate() {
		PersonDAO personDao = new PersonCollections();
		Person p = personDao.getById(1);
		p.setUsername("Cat");
		personDao.update(p);
		assertEquals(p, personDao.getById(p.getId()));
		System.out.println(personDao.getById(p.getId()));
	}
}
