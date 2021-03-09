package com.revature.data;

import com.revature.beans.SpecialNeed;

public interface SpecialNeedsDAO extends GenericDAO<SpecialNeed> {
	public SpecialNeed add(SpecialNeed sn);
}
