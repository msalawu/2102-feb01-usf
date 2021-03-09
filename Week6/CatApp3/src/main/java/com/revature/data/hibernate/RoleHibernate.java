package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.Role;
import com.revature.data.RoleDAO;
import com.revature.utils.HibernateUtil;

@Repository
public class RoleHibernate implements RoleDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Role getById(Integer id) {
		Session s = hu.getSession();
		Role r = s.get(Role.class, id);
		s.close();
		return r;
	}

	@Override
	public Set<Role> getAll() {
		Session s = hu.getSession();
		String query = "FROM Role";
		Query<Role> q = s.createQuery(query, Role.class);
		List<Role> roleList = q.getResultList();
		Set<Role> roleSet = new HashSet<>();
		roleSet.addAll(roleList);
		s.close();
		return roleSet;
	}

	@Override
	public void update(Role t) {
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
	public void delete(Role t) {
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
	public Role add(Role r) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(r);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return r;
	}

}
