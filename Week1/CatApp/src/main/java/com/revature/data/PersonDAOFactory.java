package com.revature.data;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
        
        return new PersonCollections();
    }
}
