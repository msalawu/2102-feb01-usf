package com.revature.app.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.app.beans.Breed;
import com.revature.app.beans.Cat;
import com.revature.app.beans.Person;
import com.revature.app.beans.SpecialNeed;
import com.revature.app.beans.Status;
import com.revature.app.data.BreedDAO;
import com.revature.app.data.CatDAO;
import com.revature.app.data.PersonDAO;
import com.revature.app.data.SpecialNeedDAO;
import com.revature.app.data.StatusDAO;
import com.revature.app.exceptions.CatAlreadyAdoptedException;

@Service
public class CatServiceImpl implements CatService {
	// Data Access Object
	private CatDAO catDao;
	private PersonDAO personDao;
	private BreedDAO breedDao;
	private StatusDAO statusDao;
	private SpecialNeedDAO specialNeedsDao;
	
	@Autowired
	public CatServiceImpl(CatDAO c, PersonDAO p, BreedDAO b, StatusDAO s, SpecialNeedDAO sn) {
		catDao = c;
		personDao = p;
		breedDao = b;
		statusDao = s;
		specialNeedsDao = sn;
	}

	@Override
	public Integer addCat(Cat c) {
		return catDao.save(c).getId();
	}

	@Override
	public Cat getCatById(Integer id) {
		return catDao.getOne(id);
	}

	@Override
	public Set<Cat> getAllCats() {
		List<Cat> catList = catDao.findAll();
		Set<Cat> catSet = new HashSet<>();
		catSet.addAll(catList);
		return catSet;
	}

	@Override
	public Set<Cat> getAvailableCats() {
		Status s = statusDao.findByName("Available");
		return catDao.findByStatus(s);
	}

	@Override
	public void updateCat(Cat c) {
		if (getCatById(c.getId()) != null)
			catDao.delete(c);
	}

	@Override
	public void removeCat(Cat c) {
		if (getCatById(c.getId()) != null)
			catDao.delete(c);
	}
	
	@Override
	@Transactional
	public void adoptCat(Person p, Cat c) throws CatAlreadyAdoptedException {
		if (c.getStatus().getName() != "Adopted") {
			Status s = statusDao.findByName("Adopted");
			c.setStatus(s);
			catDao.save(c);
			p = personDao.getOne(p.getId());
			Set<Cat> cats = p.getCats();
			cats.add(c);
			p.setCats(cats);
			personDao.save(p);
		} else {
			throw new CatAlreadyAdoptedException();
		}
	}

	@Override
	public Set<Breed> getAllBreeds() {
		List<Breed> breedList = breedDao.findAll();
		Set<Breed> breedSet = new HashSet<>();
		breedSet.addAll(breedList);
		return breedSet;
	}

	@Override
	public Breed getBreedById(Integer id) {
		return breedDao.getOne(id);
	}

	@Override
	public void addBreed(Breed b) {
		breedDao.save(b);
	}

	@Override
	public Set<Status> getAllStatuses() {
		List<Status> statusList = statusDao.findAll();
		Set<Status> statusSet = new HashSet<>();
		statusSet.addAll(statusList);
		return statusSet;
	}

	@Override
	public Status getStatusById(Integer id) {
		return statusDao.getOne(id);
	}
	
	@Override
	public SpecialNeed addSpecialNeed(SpecialNeed sn) {
		return specialNeedsDao.save(sn);
	}
	
	@Override
	public Set<SpecialNeed> getAllSpecialNeeds() {
		List<SpecialNeed> specialNeedList = specialNeedsDao.findAll();
		Set<SpecialNeed> specialNeedSet = new HashSet<>();
		specialNeedSet.addAll(specialNeedList);
		return specialNeedSet;
	}

}
