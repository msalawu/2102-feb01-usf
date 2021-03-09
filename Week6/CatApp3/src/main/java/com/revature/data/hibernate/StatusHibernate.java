package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.SpecialNeed;
import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import com.revature.utils.HibernateUtil;

@Repository
public class StatusHibernate implements StatusDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Status getById(Integer id) {
		Session s = hu.getSession();
		Status st = s.get(Status.class, id);
		s.close();
		return st;
	}

	@Override
	public Set<Status> getAll() {
		Session s = hu.getSession();
		String query = "FROM Status";
		Query<Status> q = s.createQuery(query, Status.class);
		List<Status> statusList = q.getResultList();
		Set<Status> statusSet = new HashSet<>();
		statusSet.addAll(statusList);
		s.close();
		return statusSet;
	}

	@Override
	public void update(Status t) {
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
	public void delete(Status t) {
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
	public Status add(Status st) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(st);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return st;
	}

}
