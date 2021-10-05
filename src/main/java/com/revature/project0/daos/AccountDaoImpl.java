package com.revature.project0.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.models.Account;

public class AccountDaoImpl implements AccountDao{
	
	private BankDBConnection bankCon;
	
	public AccountDaoImpl() {
	}

	public AccountDaoImpl(BankDBConnection bankCon) {
		this.bankCon = bankCon;
	}

	@Override
	public List<Account> getAll() {
		List<Account> accountList = new ArrayList<Account>();
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from accounts";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				accountList.add(new Account(result.getInt(1), result.getFloat(2), result.getInt(3), result.getBoolean(4)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}

	@Override
	public void update(Account entity) {
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "{? = call update_account(?,?,?,?)}";
			CallableStatement statement = con.prepareCall(sql);
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setInt(2, entity.getAccountId());
			statement.setFloat(3, entity.getBalance());
			statement.setInt(4, entity.getUserId());
			statement.setBoolean(5, entity.isApproved());
			statement.execute();
			
			System.out.println(statement.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account insert(Account entity) {
		Account newEntity = new Account();
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "{? = call insert_account(?,?,?)}";
			CallableStatement  statement = con.prepareCall(sql);
			statement.registerOutParameter(1, Types.INTEGER);
			statement.setFloat(2, entity.getBalance());
			statement.setInt(3, entity.getUserId());
			statement.setBoolean(4, entity.isApproved());
			statement.execute();
			
			
			newEntity = getById(statement.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newEntity;
		
	}


	@Override
	public void delete(Account entity) {
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "{? = call delete_account(?)}";
			CallableStatement statement = con.prepareCall(sql);
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setInt(2, entity.getAccountId());
			statement.execute();
			
			System.out.println(statement.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account getByName(String name) {
		System.out.println("Not acceptable for this class");
		return null;
	}

	@Override
	public Account getById(int id) {
		Account account = new Account();
		
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from accounts a where a.accountid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				account = new Account(result.getInt(1), result.getFloat(2), result.getInt(3), result.getBoolean(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public void insertJunction(int accountId, int userId) {
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "{? = call make_joint(?, ?)}";
			CallableStatement statement = con.prepareCall(sql);
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setInt(2, accountId);
			statement.setInt(3, userId);
			statement.execute();
			
			System.out.println(statement.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> getAccountsByUserID(int id) {
		List<Account> accountList = new ArrayList<Account>();
		
		try (Connection con = bankCon.getDBConnection()) {
			String sql = "select * from accounts a where a.userid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				accountList.add(new Account(result.getInt(1),result.getFloat(2),result.getInt(3), result.getBoolean(4)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}

	@Override
	public List<Account> getJointByUserID(int id) {
		List<Account> accountList = new ArrayList<Account>();
		
		try (Connection con = bankCon.getDBConnection()) {
			String sql = "select ja.accountid from joint_accounts ja where ja.userid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				accountList.add(getById(result.getInt(1)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}
	

}
