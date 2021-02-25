package com.revature.data.hibernate;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

public class PersonHibernate implements PersonDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person add(Person p) throws NonUniqueUsernameException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getByUsername(String username) {
		Session s = hu.getSession();
		// Criteria API: make queries w/ programmatic syntax
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForUsername = cb.equal(root.get("username"), username);
		// Predicate predicateForPassword = cb.equal(root.get("password"), password);
		// Predicate predicateForBoth = cb.and(predicateForUsername, predicateForPassword);
		
		criteria.select(root).where(predicateForUsername);
		
		Person p = s.createQuery(criteria).getSingleResult();
		return p;
	}

}
