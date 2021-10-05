package com.revature.project0.models;

public class Role {
	
	private int roleID;
	private String roleName;
	
	public Role() {
	};
	
	public Role(String roleName) {
		this.roleName = roleName;
	};
	
	public Role(int roleID, String roleName) {
		this.roleID = roleID;
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleID() {
		return roleID;
	}

	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName + "]";
	};
	

}
