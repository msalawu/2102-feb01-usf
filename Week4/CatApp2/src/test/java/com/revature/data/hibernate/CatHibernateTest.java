package com.revature.data.hibernate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Cat;

public class CatHibernateTest {
	public static CatHibernate catDao;
	
	@BeforeAll
	public static void setup() {
		catDao = new CatHibernate();
	}
	
	@Test
	public void checkGetCatById() {
		Cat c = catDao.getById(1);
		System.out.println(c);
	}
}
