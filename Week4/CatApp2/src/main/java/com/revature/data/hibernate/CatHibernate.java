package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.data.CatDAO;
import com.revature.exceptions.CatAlreadyAdoptedException;
import com.revature.utils.HibernateUtil;

public class CatHibernate implements CatDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Cat getById(Integer id) {
		Session s = hu.getSession();
		Cat c = s.get(Cat.class, id);
		s.close();
		return c;
	}

	@Override
	public Set<Cat> getAll() {
		Session s = hu.getSession();
		String query = "FROM Cat";
		Query<Cat> q = s.createQuery(query, Cat.class);
		List<Cat> catsList = q.getResultList();
		Set<Cat> cats = new HashSet<>();
		cats.addAll(catsList);
		s.close();
		return cats;
	}

	@Override
	public void update(Cat t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Cat t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public Cat add(Cat c) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(c);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return c;
	}

	@Override
	public Set<Cat> getAvailableCats() {
		Session s = hu.getSession();
		String query = "FROM Cat where status.name = :status";
		Query<Cat> q = s.createQuery(query, Cat.class);
		q.setParameter("status", "Available");
		List<Cat> catsList = q.getResultList();
		Set<Cat> cats = new HashSet<>();
		cats.addAll(catsList);
		s.close();
		return cats;
	}

	@Override
	public void adoptCat(Person p, Cat c) throws CatAlreadyAdoptedException {
		Session s = hu.getSession();
		String sql = "call adopt_cat(:personid, :catid)";
		NativeQuery nq = s.createNativeQuery(sql);
		nq.setParameter("personid", p.getId());
		nq.setParameter("catid", c.getId());
		nq.executeUpdate();
		s.close();
	}

}
