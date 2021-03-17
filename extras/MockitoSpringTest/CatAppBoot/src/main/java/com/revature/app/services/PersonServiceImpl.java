package com.revature.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Person;
import com.revature.app.data.PersonDAO;

@Service
public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	@Autowired
	public PersonServiceImpl(PersonDAO p) {
		personDao = p;
	}

	public Integer addPerson(Person p) {
		return personDao.save(p).getId();
	}

	public Person getPersonById(Integer id) {
		return personDao.getOne(id);
	}

	public Person getPersonByUsername(String username) {
		return personDao.findByUsername(username);
	}

	public void updatePerson(Person p) {
		if (getPersonById(p.getId()) != null)
			personDao.save(p);
	}

	public void deletePerson(Person p) {
		personDao.delete(p);
	}

}
