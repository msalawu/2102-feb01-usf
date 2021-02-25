package com.revature.data;

import com.revature.data.hibernate.*;

import com.revature.data.postgres.*;

public class DAOFactory {
    
    public static CatDAO getCatDAO() {
        //return new CatHibernate();
    	return new CatPostgres();
    }
    
    public static PersonDAO getPersonDAO() {
    	//return new PersonHibernate();
    	return new PersonPostgres();
    }
    
    public static BreedDAO getBreedDAO() {
    	//return new BreedHibernate();
    	return new BreedPostgres();
    }
    
    public static StatusDAO getStatusDAO() {
    	//return new StatusHibernate();
    	return new StatusPostgres();
    }
    
    public static SpecialNeedsDAO getSpecialNeedsDAO() {
    	//return new SpecialNeedsHibernate();
    	return null;
    }
    
    public static RoleDAO getRoleDAO() {
    	//return new RoleHibernate();
    	return null;
    }
}
