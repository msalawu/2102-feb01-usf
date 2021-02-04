package com.revature.data;

public class DAOFactory {
    
    public static CatDAO getCatDAO() {
        
        return new CatCollections();
    }
    
    public static PersonDAO getPersonDAO() {
    	
    	return new PersonCollections();
    }
}
