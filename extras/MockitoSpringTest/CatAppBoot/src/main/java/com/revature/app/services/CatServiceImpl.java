package com.revature.app.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Breed;
import com.revature.app.beans.Cat;
import com.revature.app.beans.Person;
import com.revature.app.beans.Status;
import com.revature.app.data.BreedDAO;
import com.revature.app.data.CatDAO;
import com.revature.app.data.PersonDAO;
import com.revature.app.data.StatusDAO;

@Service
public class CatServiceImpl implements CatService {
	private CatDAO catDao;
	private StatusDAO statusDao;
	private BreedDAO breedDao;
	private PersonDAO personDao;
	
	@Autowired
	public CatServiceImpl(CatDAO c, StatusDAO s, BreedDAO b, PersonDAO p) {
		catDao = c;
		statusDao = s;
		breedDao = b;
		personDao = p;
	}

	public Integer addCat(Cat c) {
		return catDao.save(c).getId();
	}

	public Cat getCatById(Integer id) {
		return catDao.getOne(id);
	}

	public Set<Cat> getCats() {
		List<Cat> list = catDao.findAll();
		Set<Cat> set = new HashSet<>();
		set.addAll(list);
		return set;
	}

	public Set<Cat> getAvailableCats() {
		Status s = statusDao.findByName("Available");
		return catDao.findByStatus(s);
	}

	public Set<Breed> getBreeds() {
		List<Breed> list = breedDao.findAll();
		Set<Breed> set = new HashSet<>();
		set.addAll(list);
		return set;
	}

	public void updateCat(Cat c) {
		if (getCatById(c.getId()) != null)
			catDao.save(c);
	}

	@Transactional
	public void adoptCat(Person p, Cat c) {
		Status s = statusDao.findByName("Adopted");
		c.setStatus(s);
		catDao.save(c);
		Set<Cat> cats = p.getCats();
		cats.add(c);
		p.setCats(cats);
		personDao.save(p);
	}

	public void removeCat(Cat c) {
		catDao.delete(c);
	}

}
