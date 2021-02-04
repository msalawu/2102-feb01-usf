package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Status;

public class CatCollections implements CatDAO {
	private static Set<Cat> cats;
	
	public CatCollections() {
		cats = new HashSet<Cat>();
		Cat c = new Cat();
		c.setId(1);
		c.setName("Fluffy");
		c.setAge(3);
		Breed b = new Breed();
		b.setId(1);
		b.setName("Persian");
		c.setBreed(b);
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
		c.setStatus(s);
		cats.add(c);
		
		c = new Cat();
		c.setId(2);
		c.setName("Lucky");
		c.setAge(7);
		b = new Breed();
		b.setId(2);
		b.setName("Domestic Shorthair");
		c.setBreed(b);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		c.setStatus(s);
		cats.add(c);
		
		c = new Cat();
		c.setId(3);
		c.setName("Howard");
		c.setAge(1);
		b = new Breed();
		b.setId(3);
		b.setName("Calico");
		c.setBreed(b);
		s = new Status();
		s.setId(2);
		s.setName("Adopted");
		c.setStatus(s);
		cats.add(c);
	}

	@Override
    public Cat add(Cat t) {
		// TODO update id
		cats.add(t);
		return t;
    }
    @Override
    public Cat getById(Integer id) {
        for(Cat cat : cats) {
            if(cat.getId().equals(id)) {
                return cat;
            }
        }
        return null;
    }
    @Override
    public Set<Cat> getAll() {
        return cats;
    }
    
    @Override
    public Set<Cat> getAvailableCats() {
        Set<Cat> acats = new HashSet<>();
        for(Cat cat : cats) {
        	// TODO needed to get the name from status
            if(cat.getStatus().getName().equals("Available")) {
                acats.add(cat);
            }
        }
        return acats;
    }
    @Override
    public void update(Cat t) {
//    	if (c.getId() < cats.size()) {
//			for (Cat cat : cats) {
//				if (cat.getId().equals(c.getId())) {
//					cat.setName(c.getName());
//					cat.setAge(c.getAge());
//					cat.setBreed(c.getBreed());
//					cat.setStatus(c.getStatus());
//				}
//			}
//		} else {
//			add(c);
//		}
    	
    	// TODO check whether the cat exists first
    	Cat match = getById(t.getId());
    	if (match != null) {
    		match.setAge(t.getAge());
    		match.setBreed(t.getBreed());
    		match.setId(t.getId());
    		match.setName(t.getName());
    		match.setSpecialNeeds(t.getSpecialNeeds());
    		match.setStatus(t.getStatus());
    	}
    }
    @Override
    public void delete(Cat t) {
        if(cats.contains(t)) {
            cats.remove(t);
        }
    }

	@Override
	public void adoptCat(Person p, Cat c) {
		// TODO Auto-generated method stub
		
	}


}
