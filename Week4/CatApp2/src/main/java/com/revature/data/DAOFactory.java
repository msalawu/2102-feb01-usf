package com.revature.data;

import com.revature.data.hibernate.BreedHibernate;
import com.revature.data.hibernate.CatHibernate;
import com.revature.data.hibernate.PersonHibernate;
import com.revature.data.hibernate.RoleHibernate;
import com.revature.data.hibernate.SpecialNeedsHibernate;
import com.revature.data.hibernate.StatusHibernate;

public class DAOFactory {
    
    public static CatDAO getCatDAO() {
        return new CatHibernate();
    }
    
    public static PersonDAO getPersonDAO() {
    	return new PersonHibernate();
    }
    
    public static BreedDAO getBreedDAO() {
    	return new BreedHibernate();
    }
    
    public static StatusDAO getStatusDAO() {
    	return new StatusHibernate();
    }
    
    public static SpecialNeedsDAO getSpecialNeedsDAO() {
    	return new SpecialNeedsHibernate();
    }
    
    public static RoleDAO getRoleDAO() {
    	return new RoleHibernate();
    }
}
