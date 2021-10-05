package com.revature.eval.project0.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.project0.daos.UserDaoImpl;
import com.revature.project0.models.User;
import com.revature.project0.services.UserService;

public class UserServiceTest {
	@Mock
	private UserDaoImpl uDao;
	private UserService uServ;
	
	private User testUser;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		uServ = new UserService(uDao);
		testUser = new User("admin", "admin", 1);
		when(uDao.getByName("admin")).thenReturn(testUser);
		when(uDao.getById(1)).thenReturn(testUser);
	}
	
	@Test
	public void testGetUserByNameSuccess() {
		assertEquals(uServ.getUserByName("admin"), testUser);
	}
	
	@Test
	public void testGetUserByIDSuccess() {
		assertEquals(uServ.getUserById(1), testUser);
	}
	
}
