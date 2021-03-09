package com.revature.data;

import com.revature.data.hibernate.*;

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
