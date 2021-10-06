package com.revature.eval.project0.services;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.project0.daos.RoleDaoImpl;
import com.revature.project0.models.Role;
import com.revature.project0.services.RoleService;

public class RoleServiceTest {
	@Mock
	private RoleDaoImpl rDao;
	private RoleService rServ;
	private Role testRole;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		rServ = new RoleService(rDao);
		testRole = new Role(2,"Employee");
		when(rDao.getByName("Employee")).thenReturn(testRole);
		when(rDao.getById(2)).thenReturn(testRole);
	}
	
	@Test
	public void testGetRoleByNameSuccess() {
		assertEquals(rServ.getRoleByName("Employee"), testRole);
	}
	
	@Test
	public void  testGetRoleByIdSuccess() {
		assertEquals(rServ.getRoleById(2), testRole);
	}
	
	@Test
	public void testGetRoleByIdUnsuccess() {
		assertThrows(NullPointerException.class, () -> rServ.getRoleById(10));
	}
	@Test
	public void testGetRoleByNameUnsuccess() {
		assertThrows(NullPointerException.class, () -> rServ.getRoleByName("Test"));
	}
}
