package com.revature.controllers;

import java.net.URI;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.exceptions.CatAlreadyAdoptedException;
import com.revature.services.CatService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/cats")
public class CatController {
	private CatService catServ;
	
	@Autowired
	public CatController(CatService c) {
		catServ = c;
	}
	
	// a GET request to /cats
	@GetMapping
	public ResponseEntity<Set<Cat>> getAvailableCats() {
		Set<Cat> cats = catServ.getAvailableCats();
		return ResponseEntity.ok(cats);
	}
	
	// a POST request to /cats
	@PostMapping
	public ResponseEntity<Cat> addCat(@RequestBody Cat cat) {
		Integer id = catServ.addCat(cat);
		// return ResponseEntity.status(201).build();
		return ResponseEntity.created(URI.create("http://localhost:8080/CatApp3/cats/" + id)).build();
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Cat> getCatById(@PathVariable("id") Integer id) {
		Cat cat = catServ.getCatById(id);
		return ResponseEntity.ok(cat);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Cat> updateCat(@PathVariable("id") Integer id, @RequestBody Cat cat) {
		if (cat != null && id.equals(cat.getId())) {
			catServ.updateCat(cat);
			return ResponseEntity.ok(cat);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteCat(@PathVariable("id") Integer id, @RequestBody Cat cat) {
		if (cat != null && id.equals(cat.getId())) {
			catServ.removeCat(cat);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping(path="/adopt/{id}")
	public ResponseEntity<Void> adoptCat(HttpSession session, @PathVariable("id") Integer id) {
		Person loggedInPerson = (Person) session.getAttribute("user");
		if (loggedInPerson != null) {
			Cat cat = catServ.getCatById(id);
			if (cat != null) {
				try {
					catServ.adoptCat(loggedInPerson, cat);
					return ResponseEntity.ok().build();
				} catch (CatAlreadyAdoptedException e) {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
}
