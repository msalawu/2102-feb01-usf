package com.revature.app.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Cat;
import com.revature.app.beans.Status;

@Repository
public interface CatDAO extends JpaRepository<Cat, Integer> {
	public Set<Cat> findByStatus(Status s);
}
