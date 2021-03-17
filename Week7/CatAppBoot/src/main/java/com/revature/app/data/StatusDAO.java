package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Status;

@Repository
public interface StatusDAO extends JpaRepository<Status, Integer> {
	public Status findByName(String name);
}
