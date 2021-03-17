package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {
	public Person findByUsername(String username);
}
