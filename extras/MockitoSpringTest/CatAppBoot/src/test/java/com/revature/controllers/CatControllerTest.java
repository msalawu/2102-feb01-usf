package com.revature.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.app.CatApp;
import com.revature.app.beans.Cat;
import com.revature.app.controllers.CatController;
import com.revature.app.services.CatService;

//@SpringBootTest(classes=CatApp.class)
@WebMvcTest(CatController.class)
public class CatControllerTest {
	@MockBean
	CatService catServ;
	
	@Autowired
	static MockMvc mockMvc;
	
	static Set<Cat> mockCats;
	
	@BeforeAll
	public static void setUp() {
		//mockMvc = MockMvcBuilders.standaloneSetup(CatController.class).build();
		
		mockCats = new HashSet<>();
		Cat cat = new Cat();
		cat.setId(1);
		cat.setName("Test");
		mockCats.add(cat);
	}
	
	@Test
	public void testGetAvailableCats() throws Exception {
		when(catServ.getAvailableCats()).thenReturn(mockCats);
		
		MvcResult result = mockMvc.perform(get("/cats")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andReturn();
			System.out.println(result.getResponse().getContentAsString());
	}
}
