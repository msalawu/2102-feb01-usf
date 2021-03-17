package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.SpecialNeed;

@Repository
public interface SpecialNeedDAO extends JpaRepository<SpecialNeed, Integer> {

}
