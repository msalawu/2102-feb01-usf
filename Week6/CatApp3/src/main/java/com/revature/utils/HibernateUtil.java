package com.revature.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static HibernateUtil hu;
	private static SessionFactory sessionFactory;
	
	private HibernateUtil() {
		super();
	}
	
	public synchronized static HibernateUtil getHibernateUtil() {
		if (hu == null)
			hu = new HibernateUtil();
		return hu;
	}
	
	public synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistry standardRegistry =
					new StandardServiceRegistryBuilder().configure().build();
			Metadata meta = new MetadataSources(standardRegistry)
					.getMetadataBuilder()
					.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
					.build();
			sessionFactory = meta.getSessionFactoryBuilder().build();
		}
		return sessionFactory;
	}
	
	public Session getSession() {
		return this.getSessionFactory().openSession();
	}
}
