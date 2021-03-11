package com.revature.app.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Person;
import com.revature.app.beans.Role;
import com.revature.app.data.PersonDAO;
import com.revature.app.data.RoleDAO;
import com.revature.app.exceptions.NonUniqueUsernameException;

@Service
public class PersonServiceImpl implements PersonService {
	// field injection
	// @Autowired
	private PersonDAO personDao;
	// @Autowired
	private RoleDAO roleDao;
	
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
		return personDao.save(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.getOne(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.findByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		if (getPersonById(p.getId()) != null)
			personDao.save(p);
	}

	@Override
	public void deletePerson(Person p) {
		if (getPersonById(p.getId()) != null)
			personDao.delete(p);
	}

	@Override
	public Set<Role> getAllRoles() {
		List<Role> roleList = roleDao.findAll();
		Set<Role> roleSet = new HashSet<>();
		roleSet.addAll(roleList);
		return roleSet;
	}
}
