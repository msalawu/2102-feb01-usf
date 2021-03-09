package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.SpecialNeed;
import com.revature.data.SpecialNeedsDAO;
import com.revature.utils.HibernateUtil;

@Repository
public class SpecialNeedsHibernate implements SpecialNeedsDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public SpecialNeed getById(Integer id) {
		Session s = hu.getSession();
		SpecialNeed sn = s.get(SpecialNeed.class, id);
		s.close();
		return sn;
	}

	@Override
	public Set<SpecialNeed> getAll() {
		Session s = hu.getSession();
		String query = "FROM SpecialNeed";
		Query<SpecialNeed> q = s.createQuery(query, SpecialNeed.class);
		List<SpecialNeed> snList = q.getResultList();
		Set<SpecialNeed> snSet = new HashSet<>();
		snSet.addAll(snList);
		s.close();
		return snSet;
	}

	@Override
	public void update(SpecialNeed t) {
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
	public void delete(SpecialNeed t) {
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
	public SpecialNeed add(SpecialNeed sn) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(sn);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return sn;
	}

}
