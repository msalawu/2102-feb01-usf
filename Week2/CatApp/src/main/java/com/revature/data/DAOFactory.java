package com.revature.data;

public class DAOFactory {
    
    public static CatDAO getCatDAO() {
        return new CatPostgres();
    }
    
    public static PersonDAO getPersonDAO() {
    	return new PersonPostgres();
    }
    
    public static BreedDAO getBreedDAO() {
    	return new BreedPostgres();
    }
    
    public static StatusDAO getStatusDAO() {
    	return new StatusPostgres();
    }
}
