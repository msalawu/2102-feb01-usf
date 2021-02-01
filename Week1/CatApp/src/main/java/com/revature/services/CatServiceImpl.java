package com.revature.services;

import java.util.Set;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.CatDAO;
import com.revature.data.CatDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class CatServiceImpl implements CatService {
	private CatDAO catDao;
	private PersonDAO personDao;
	
	public CatServiceImpl() {
		CatDAOFactory catDaoFactory = new CatDAOFactory();
		catDao = catDaoFactory.getCatDAO();
		
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	@Override
    public Integer addCat(Cat c) {
        return catDao.add(c).getId();
    }
    @Override
    public Cat getCatById(Integer id) {
        return catDao.getById(id);
    }
    @Override
    public Set<Cat> getCats() {
        return catDao.getAll();
    }
    @Override
    public Set<Cat> getAvailableCats() {
        return catDao.getAvailableCats();
    }
    @Override
    public void updateCat(Cat c) {
        catDao.update(c);   
    }
    @Override
    public void adoptCat(Person p, Cat c) {
    	Status status = new Status();
    	status.setId(2);
    	status.setName("Adopted");
    	c.setStatus(status);
    	updateCat(c);
        Set<Cat> set = p.getCats();
        set.add(c);
        p.setCats(set);
        personDao.update(p);
        
    }
    @Override
    public void removeCat(Cat c) {
        catDao.delete(c);
    }

}
