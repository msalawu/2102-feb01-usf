package com.horror.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horror.dao.HorrorRepo;
import com.horror.model.Horror;

@RestController
@RequestMapping("/horror")
public class HorrorController {

	@Autowired
	private HorrorRepo hr;
	
	@GetMapping
	public List<Horror> findAll(){
		return hr.findAll(); 
	}
	
	@PostMapping
	public String save(@RequestBody Horror h) {
		hr.save(h);
		return "saved";
	}
	
	
	
	
	
	
}
