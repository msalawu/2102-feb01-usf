package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Breed;

@Repository
public interface BreedDAO extends JpaRepository<Breed, Integer> {

}
