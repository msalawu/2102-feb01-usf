package com.romance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romance.dao.RomanceRepo;
import com.romance.model.Romance;

@RestController
@RequestMapping("/romance")
public class RomanceController {

	@Autowired
	private RomanceRepo rr;
	
	@GetMapping
	public List<Romance> findAll(){
		return rr.findAll(); 
	}
	
	@PostMapping
	public String save(@RequestBody Romance r) {
		rr.save(r);
		return "saved";
	}
	
	
	
	
}
