package com.revature.app.controllers;

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

import com.revature.app.beans.Cat;
import com.revature.app.services.CatService;
import com.revature.app.services.PersonService;
import com.revature.app.beans.SpecialNeed;
import com.revature.app.beans.Breed;
import com.revature.app.beans.Person;
import com.revature.app.exceptions.CatAlreadyAdoptedException;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/cats")
public class CatController {
	private final CatService catServ;
	private final PersonService personServ;

	@Autowired
	public CatController(CatService c, PersonService p) {
		this.catServ = c;
		this.personServ = p;
	}
	
	@GetMapping
	public ResponseEntity<Set<Cat>> getAvailableCats() {
		Set<Cat> cats = catServ.getAvailableCats();
		return ResponseEntity.ok(cats);
	}
	
	@PostMapping
	public ResponseEntity<Cat> addCat(@RequestBody Cat cat) {
		Integer id = catServ.addCat(cat);
		return ResponseEntity.created(URI.create("http://localhost:8080/CatApp3/cats/" + id)).build();
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<Set<Cat>> getAllCats() {
		Set<Cat> cats = catServ.getAllCats();
		return ResponseEntity.ok(cats);
	}
	
	@PutMapping(path="/adopt/{id}")
	public ResponseEntity<Void> adoptCat(HttpSession session, @PathVariable("id") Integer id) {
		Person loggedPerson = (Person) session.getAttribute("user");
		if (loggedPerson != null) {
			Cat cat = catServ.getCatById(id);
			if (cat != null && cat.getStatus().getName().equals("Available")) {
				try {
					catServ.adoptCat(loggedPerson, cat);
					loggedPerson = personServ.getPersonById(loggedPerson.getId());
					session.setAttribute("user", loggedPerson);
				} catch (CatAlreadyAdoptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Cat> getById(@PathVariable("id") Integer id) {
		Cat cat = catServ.getCatById(id);
		if (cat != null) {
			return ResponseEntity.ok(cat);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateCat(@PathVariable("id") Integer id, @RequestBody Cat cat) {
		if (cat != null && id.equals(cat.getId())) {
			catServ.updateCat(cat);
			return ResponseEntity.ok().build();
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
	
	@GetMapping(path="/breeds")
	public ResponseEntity<Set<Breed>> getBreeds() {
		Set<Breed> breeds = catServ.getAllBreeds();
		return ResponseEntity.ok(breeds);
	}

	@GetMapping(path="/specialneeds")
	public ResponseEntity<Set<SpecialNeed>> getSpecialNeeds() {
		Set<SpecialNeed> needs = catServ.getAllSpecialNeeds();
		return ResponseEntity.ok(needs);
	}
}
