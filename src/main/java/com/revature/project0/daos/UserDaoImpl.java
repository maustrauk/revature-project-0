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
import com.revature.project0.models.User;

public class UserDaoImpl implements UserDao{
	
	private BankDBConnection bankCon;
	
	private AccountDaoImpl accountDao;
	
	public UserDaoImpl() {
	}
	
	public UserDaoImpl(BankDBConnection bankCon) {
		this.bankCon = bankCon;
		this.accountDao = new AccountDaoImpl(bankCon);
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from users";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				List<Account> accountList = accountDao.getAccountsByUserID(result.getInt(1));
				userList.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), accountList));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	

	@Override
	public User getByName(String name) {
		User user = new User();
		
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from users u where u.username = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				List<Account> accountList = accountDao.getAccountsByUserID(result.getInt(1));
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), accountList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getById(int id) {
		User user = new User();
		
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "select * from users u where u.userid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				List<Account> accountList = accountDao.getAccountsByUserID(result.getInt(1));
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), accountList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(User entity) {
		
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "{? = call update_user(?,?,?,?)}";
			CallableStatement statement = con.prepareCall(sql);
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setInt(2, entity.getUserId());
			statement.setString(3, entity.getUserName());
			statement.setString(4, entity.getUserPassword());
			statement.setInt(5, entity.getRoleId());
			statement.execute();
			
			System.out.println(statement.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User insert(User entity) {
		User newEntity = new User();
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "{? = call insert_user(?,?,?)}";
			CallableStatement statement = con.prepareCall(sql);
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setString(2, entity.getUserName());
			statement.setString(3, entity.getUserPassword());
			statement.setInt(4, entity.getRoleId());
			statement.execute();
			
			System.out.println(statement.getString(1));
			newEntity = getByName(entity.getUserName());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newEntity;
	}

	@Override
	public void delete(User entity) {
		try(Connection con = bankCon.getDBConnection()) {
			String sql = "{? = call delete_user(?)}";
			CallableStatement statement = con.prepareCall(sql);
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setInt(2, entity.getUserId());
			statement.execute();
			
			System.out.println(statement.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

//	@Override
//	public void insertUser(String f_username, String f_password, String f_userrole) {
//		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			
//			String sql = "insert into users(username, userpassword) values('" + f_username + "', '" + f_password + "')";
//			
//			Statement statement = con.createStatement();
//			int changed = statement.executeUpdate(sql);
//			System.out.println("Num of rows change: " + changed);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public void preparedInsertUser(String f_username, String f_password, String f_userrole) {
//		
//		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			
//			String sql = "insert into users(username, userpassword) values(?,?)";
//			
//			PreparedStatement prepare = con.prepareStatement(sql);
//			prepare.setString(1, f_username);
//			prepare.setString(2, f_password);
//			
//			int changed = prepare.executeUpdate();
//			System.out.println("Num of rows change: " + changed);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public void callableInsertUser(String f_username, String f_password, int f_roleid) {
//		
//		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//					
//					String sql = "{? = call insert_user(?,?,?)}";
//					
//					CallableStatement cs = con.prepareCall(sql);
//					cs.registerOutParameter(1, Types.VARCHAR);
//					cs.setString(2, f_username);
//					cs.setString(3, f_password);
//					cs.setInt(4, f_roleid);
//					cs.execute();
//					
//					System.out.println(cs.getString(1));
//					
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//	@Override
//	public void insertUserObject(User user) {
//		
//		
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		
//		List<User> userList = new ArrayList<User>();
//		
//		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			String sql = "select * from users";
//			
//			PreparedStatement statement = con.prepareStatement(sql);
//			ResultSet result = statement.executeQuery();
//			
//			while(result.next()) {
//				userList.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return userList;
//	}

}
