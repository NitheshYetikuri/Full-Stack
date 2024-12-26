package com.furniturestore.controllertest;

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
import com.furniturestore.controller.RoleController;
import com.furniturestore.entity.Role;
import com.furniturestore.serviceImpl.RoleServiceImpl;

@SpringBootTest
public class RoleControllerTests {

    private MockMvc mockMvc;

    @Mock
    private RoleServiceImpl roleServiceImpl;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    public void testCreateNewRole() throws Exception {
        Role role = new Role();
        // Set role properties
        mockMvc.perform(post("/createNewRole")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(role)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        
        return new ObjectMapper().writeValueAsString(obj);
    
}
}
