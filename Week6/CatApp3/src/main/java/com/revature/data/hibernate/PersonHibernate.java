package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

@Repository
public class PersonHibernate implements PersonDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Person getById(Integer id) {
		Session s = hu.getSession();
		Person p = s.get(Person.class, id);
		s.close();
		return p;
	}

	@Override
	public Set<Person> getAll() {
		Session s = hu.getSession();
		String query = "FROM Person";
		Query<Person> q = s.createQuery(query, Person.class);
		List<Person> personList = q.getResultList();
		Set<Person> personSet = new HashSet<>();
		personSet.addAll(personList);
		s.close();
		return personSet;
	}

	@Override
	public void update(Person t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Person t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public Person add(Person p) {
		Session s = hu.getSession();
		Role r = s.get(Role.class, p.getRole().getId());
		p.setRole(r);
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			s.close();
		}
		return p;
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
