package com.furniturestore.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.furniturestore.dao.RoleDao;
import com.furniturestore.dao.UserDao;
import com.furniturestore.entity.Role;
import com.furniturestore.entity.User;
import com.furniturestore.exception.RegistrationException;
import com.furniturestore.serviceImpl.UserServiceImpl;

public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterNewUser() {
        User user = new User();
        user.setUserName("testUser");
        user.setUserPassword("testPassword");

        Role role = new Role();
        role.setRoleName("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);

        when(roleDao.findById("User")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(user.getUserPassword())).thenReturn("encodedPassword");
        when(userDao.save(user)).thenReturn(user);

        User registeredUser = userService.registerNewUser(user);

        assertEquals("testUser", registeredUser.getUserName());
        assertEquals("encodedPassword", registeredUser.getUserPassword());
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testRegisterNewUserThrowsException() {
        User user = new User();
        user.setUserName("testUser");
        user.setUserPassword("testPassword");

        when(roleDao.findById("User")).thenReturn(Optional.empty());

        assertThrows(RegistrationException.class, () -> userService.registerNewUser(user));
    }
}
