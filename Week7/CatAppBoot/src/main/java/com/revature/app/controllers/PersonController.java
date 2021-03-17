package com.revature.app.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import com.revature.app.beans.Person;
import com.revature.app.exceptions.NonUniqueUsernameException;
import com.revature.app.services.PersonService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/users")
public class PersonController {
	private PersonService personServ;
	
	@Autowired
	public PersonController(PersonService p) {
		personServ = p;
	}
	
	@GetMapping
	public ResponseEntity<Person> checkLogin(HttpSession session) {
		Person loggedPerson = (Person) session.getAttribute("user");
		if (loggedPerson == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(loggedPerson);
	}
	
	@PutMapping
	public ResponseEntity<Person> logIn(HttpSession session, @RequestBody Map<String, String> loginInfo) {
		Person person = personServ.getPersonByUsername(loginInfo.get("username"));
		if (person != null) {
			if (person.getPassword().equals(loginInfo.get("password"))) {
				session.setAttribute("user", person);
				return ResponseEntity.ok(person);
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> registerUser(HttpSession session, @RequestBody Person person) {
		try {
			personServ.addPerson(person);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> logOut(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateUser(HttpSession session, @PathVariable("id") Integer id, 
			@RequestBody Person person) {
		Person loggedPerson = (Person) session.getAttribute("user");
		if (loggedPerson != null && loggedPerson.getId().equals(id)) {
			personServ.updatePerson(person);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
