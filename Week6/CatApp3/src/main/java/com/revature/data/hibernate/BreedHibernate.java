package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.Breed;
import com.revature.data.BreedDAO;
import com.revature.utils.HibernateUtil;

@Repository
public class BreedHibernate implements BreedDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Breed getById(Integer id) {
		Session s = hu.getSession();
		Breed b = s.get(Breed.class, id);
		s.close();
		return b;
	}

	@Override
	public Set<Breed> getAll() {
		Session s = hu.getSession();
		String query = "FROM Breed";
		Query<Breed> q = s.createQuery(query, Breed.class);
		List<Breed> breedList = q.getResultList();
		Set<Breed> breedSet = new HashSet<>();
		breedSet.addAll(breedList);
		s.close();
		return breedSet;
	}

	@Override
	public void update(Breed t) {
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
	public void delete(Breed t) {
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
	public Breed add(Breed b) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(b);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			s.close();
		}
		return b;
	}

}
