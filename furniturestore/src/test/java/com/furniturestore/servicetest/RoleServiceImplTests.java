package com.furniturestore.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.furniturestore.dao.RoleDao;
import com.furniturestore.entity.Role;
import com.furniturestore.exception.RoleCreationException;
import com.furniturestore.serviceImpl.RoleServiceImpl;

public class RoleServiceImplTests {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleDao roleDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateNewRole() {
        Role role = new Role();
        role.setRoleName("Admin");

        when(roleDao.save(role)).thenReturn(role);

        Role createdRole = roleService.createNewRole(role);

        assertEquals("Admin", createdRole.getRoleName());
        verify(roleDao, times(1)).save(role);
    }

    @Test
    public void testCreateNewRoleThrowsException() {
        Role role = new Role();
        role.setRoleName("Admin");

        when(roleDao.save(role)).thenThrow(new RuntimeException("Database error"));

        assertThrows(RoleCreationException.class, () -> roleService.createNewRole(role));
    }
}
