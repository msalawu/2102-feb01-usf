package com.revature.data;

import com.revature.beans.Status;

public interface StatusDAO extends GenericDAO<Status> {
	public Status add(Status s);
}
