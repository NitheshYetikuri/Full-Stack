package com.furniturestore.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furniturestore.controller.JwtController;
import com.furniturestore.entity.JwtRequest;
import com.furniturestore.entity.JwtResponse;
import com.furniturestore.entity.User;
import com.furniturestore.serviceImpl.JwtServiceImpl;

@SpringBootTest
public class JwtControllerTests {

    private MockMvc mockMvc;

    @Mock
    private JwtServiceImpl jwtServiceImpl;

    @InjectMocks
    private JwtController jwtController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jwtController).build();
    }

    @Test
    public void testCreateJwtToken() throws Exception {
        JwtRequest jwtRequest = new JwtRequest();
        
        User user = new User();
        String jwtToken = "your_jwt_token";
        
        // Set jwtRequest properties
        JwtResponse jwtResponse = new JwtResponse(user, jwtToken);
        // Set jwtResponse properties
        when(jwtServiceImpl.createJwtToken(jwtRequest)).thenReturn(jwtResponse);
        mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(jwtRequest)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
       
            return new ObjectMapper().writeValueAsString(obj);
        
    }
}
