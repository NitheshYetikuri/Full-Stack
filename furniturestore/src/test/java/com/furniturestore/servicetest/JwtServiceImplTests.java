package com.furniturestore.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.furniturestore.dao.UserDao;
import com.furniturestore.entity.JwtRequest;
import com.furniturestore.entity.JwtResponse;
import com.furniturestore.entity.Role;
import com.furniturestore.entity.User;
import com.furniturestore.serviceImpl.JwtServiceImpl;
import com.furniturestore.util.JwtUtil;

public class JwtServiceImplTests {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDao userDao;

    @Mock
    private AuthenticationManager authenticationManager;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateJwtToken() throws Exception {
        String userName = "testUser";
        String userPassword = "testPassword";
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName(userName);
        jwtRequest.setUserPassword(userPassword);
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        String token = "testToken";

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));
        user.setRole(roles);  // Initialize the Role set for the User object

        when(userDao.findById(userName)).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(any(UserDetails.class))).thenReturn(token);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);

        JwtResponse jwtResponse = jwtService.createJwtToken(jwtRequest);

        assertEquals(user, jwtResponse.getUser());
        assertEquals(token, jwtResponse.getJwtToken());
    }


    @Test
    public void testLoadUserByUsername() {
        String userName = "pushpa";
        String userPassword = "pushpa@88";
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));
        user.setRole(roles);

        when(userDao.findById(userName)).thenReturn(Optional.of(user));

        UserDetails userDetails = jwtService.loadUserByUsername(userName);

        assertEquals(userName, userDetails.getUsername());
    }

}
