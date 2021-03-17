package com.revature.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.app.CatApp;
import com.revature.app.beans.Cat;
import com.revature.app.beans.Person;
import com.revature.app.beans.Status;
import com.revature.app.data.CatDAO;
import com.revature.app.data.PersonDAO;
import com.revature.app.data.StatusDAO;
import com.revature.app.services.CatService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes=CatApp.class)
public class CatServiceTest {
	@Autowired
	CatService catServ;
	
	@MockBean
	CatDAO catDao;
	
	@MockBean
	StatusDAO statusDao;
	
	@MockBean
	PersonDAO personDao;
	
	static List<Cat> mockCats;
	static Set<Cat> mockAvailableCats;
	static Cat mockCat1;
	static Cat mockCat2;
	
	@BeforeAll
	public static void setUp() {
		mockCats = new LinkedList<>();
		
		mockCat1 = new Cat();
		mockCat1.setId(1);
		mockCat1.setName("Test");
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
		mockCat1.setStatus(s);
		
		mockCat2 = new Cat();
		mockCat2.setId(2);
		mockCat2.setName("Test");
		Status st = new Status();
		st.setId(2);
		st.setName("Adopted");
		mockCat1.setStatus(st);
		
		mockCats.add(mockCat1);
		mockCats.add(mockCat2);
		
		mockAvailableCats = new HashSet<>();
		mockAvailableCats.add(mockCat1);
	}
	
	@Test
	public void testGetById() {
		when(catDao.getOne(1)).thenReturn(mockCat1);
		
		assertEquals(mockCat1, catServ.getCatById(1));
		verify(catDao).getOne(1);
	}
	
	@Test
	public void testGetAll() {
		when(catDao.findAll()).thenReturn(mockCats);
		
		Set<Cat> mockCatSet = new HashSet<>();
		mockCatSet.addAll(mockCats);
		
		assertEquals(mockCatSet, catServ.getCats());
		verify(catDao).findAll();
	}
	
	@Test
	public void testGetAvailableCats() {
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
		when(statusDao.findByName("Available")).thenReturn(s);
		when(catDao.findByStatus(s)).thenReturn(mockAvailableCats);
		
		assertEquals(mockAvailableCats, catServ.getAvailableCats());
		verify(statusDao).findByName("Available");
		verify(catDao).findByStatus(s);
	}
	
	@Test
	public void testUpdateCat() {
		when(catDao.getOne(1)).thenReturn(mockCat1);
		when(catDao.save(mockCat1)).thenAnswer(inv -> {
			mockCat1.setName("Update");
			return mockCat1;
		});
		
		catServ.updateCat(mockCat1);
		assertEquals("Update", mockCat1.getName());
	}
	
	@Test
	public void testAdoptCat() {
		Person mockPerson = new Person();
		Status s = new Status();
		s.setId(2);
		s.setName("Adopted");
		when(statusDao.findByName("Adopted")).thenReturn(s);
		mockCat1.setStatus(s);
		Set<Cat> cats = new HashSet<>();
		mockPerson.setCats(cats);
		when(catDao.save(mockCat1)).thenReturn(mockCat1);
		when(personDao.save(mockPerson)).thenReturn(mockPerson);
		
		catServ.adoptCat(mockPerson, mockCat1);
		verify(catDao).save(mockCat1);
		verify(personDao).save(mockPerson);
	}
}
