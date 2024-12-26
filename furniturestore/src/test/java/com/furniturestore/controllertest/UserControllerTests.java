package com.furniturestore.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.furniturestore.controller.UserController;
import com.furniturestore.entity.User;
import com.furniturestore.serviceImpl.UserServiceImpl;

@SpringBootTest
public class UserControllerTests {

    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testRegisterNewUser() throws Exception {
        User user = new User();
        // Set user properties
        mockMvc.perform(post("/registerNewUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testForAdmin() throws Exception {
        mockMvc.perform(get("/forAdmin"))
                .andExpect(status().isOk());
    }

    @Test
    public void testForUser() throws Exception {
        mockMvc.perform(get("/forUser"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        
        return new ObjectMapper().writeValueAsString(obj);
    
}
}
