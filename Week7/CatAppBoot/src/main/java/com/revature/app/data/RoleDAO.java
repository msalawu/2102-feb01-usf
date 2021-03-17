package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {

}
