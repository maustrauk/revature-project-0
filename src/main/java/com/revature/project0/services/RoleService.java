package com.revature.project0.services;

import java.util.List;

import com.revature.project0.daos.RoleDaoImpl;
import com.revature.project0.models.Role;

public class RoleService {
	private RoleDaoImpl rDao;

	public RoleService() {
	}
	
	public RoleService(RoleDaoImpl rDao) {
		this.rDao = rDao;
	}
	
	public List<Role> getAllRoles() {
		return rDao.getAll();
	}
	
	public Role getRoleByName(String name) {
		Role role = rDao.getByName(name);
		if (role == null) {
			throw new NullPointerException("There isn't a role with name: " + name);
		}
		return role;
	}
	
	public Role getRoleById(int id) {
		Role role = rDao.getById(id);
		if (role == null) {
			throw new NullPointerException("There isn't a role with id: " + id);
		}
		return role;
	}
	
}
