package com.revature.data;

public class CatDAOFactory {
    
    public CatDAO getCatDAO() {
        
        return new CatCollections();
    }
}
