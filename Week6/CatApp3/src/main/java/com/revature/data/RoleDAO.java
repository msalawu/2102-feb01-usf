package com.revature.data;

import com.revature.beans.Role;

public interface RoleDAO extends GenericDAO<Role> {
	public Role add(Role r);
}
