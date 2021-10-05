package com.revature.project0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.models.Role;

public class RoleDaoImpl implements RoleDao{
	
	private BankDBConnection bankCon;
	
	public RoleDaoImpl() {
	}

	public RoleDaoImpl(BankDBConnection bankCon) {
		this.bankCon = bankCon;
	}

	@Override
	public List<Role> getAll() {
		List<Role> roleList = new ArrayList<Role>();
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from roles";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				roleList.add(new Role(result.getInt(1), result.getString(2)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	@Override
	public Role getByName(String name) {
		
		Role role = new Role();
		
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from roles r where r.rolename = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				role = new Role(result.getInt(1), result.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public Role getById(int id) {
		Role role = new Role();
		
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from roles r where r.roleid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				role = new Role(result.getInt(1), result.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public void update(Role entity) {
		System.out.println("Not acceptable for this class");
	}

	@Override
	public Role insert(Role entity) {
		System.out.println("Not acceptable for this class");
		return null;
	}

	@Override
	public void delete(Role entity) {
		System.out.println("Not acceptable for this class");
	}
	

}
