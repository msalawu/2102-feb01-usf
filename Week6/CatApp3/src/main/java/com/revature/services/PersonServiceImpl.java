package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.data.DAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.RoleDAO;
import com.revature.exceptions.NonUniqueUsernameException;

@Service
public class PersonServiceImpl implements PersonService {
	// field injection
	// @Autowired
	private PersonDAO personDao;
	// @Autowired
	private RoleDAO roleDao;
	
	private static Logger log = Logger.getLogger(PersonServiceImpl.class);
	
	// constructor injection
	@Autowired
	public PersonServiceImpl(PersonDAO pDao, RoleDAO rDao) {
		personDao = pDao;
		roleDao = rDao;
	}
	
	// setter injection
	// @Autowired
//	public void setPersonDao(PersonDAO p) {
//		personDao = p;
//	}
	
	// @Autowired
//	public void setRoleDao(RoleDAO r) {
//		roleDao = r;
//	}

	@Override
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.getByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		if (getPersonById(p.getId()) != null)
			personDao.update(p);
		else
			log.debug("Person didn't exist in the database.");
	}

	@Override
	public void deletePerson(Person p) {
		if (getPersonById(p.getId()) != null)
			personDao.delete(p);
		else
			log.debug("Person didn't exist in the database.");
	}

	@Override
	public Set<Role> getAllRoles() {
		return roleDao.getAll();
	}
}
