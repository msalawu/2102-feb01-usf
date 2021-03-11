package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {
	public Person findByUsername(String username);
	
//	@Query(value = "select * from person where username = :thing", nativeQuery=true)
//	public Person findBySomething(@Param("thing") String something);
}
