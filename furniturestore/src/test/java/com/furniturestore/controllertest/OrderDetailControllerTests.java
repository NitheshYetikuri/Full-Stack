package com.furniturestore.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

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
import com.furniturestore.controller.OrderDetailController;
import com.furniturestore.entity.OrderInput;
import com.furniturestore.serviceImpl.OrderDetailServiceImpl;

@SpringBootTest
public class OrderDetailControllerTests {

    private MockMvc mockMvc;

    @Mock
    private OrderDetailServiceImpl orderDetailServiceImpl;

    @InjectMocks
    private OrderDetailController orderDetailController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderDetailController).build();
    }

    @Test
    public void testPlaceOrder() throws Exception {
        OrderInput orderInput = new OrderInput();
        // Set orderInput properties
        mockMvc.perform(post("/placeOrder/true")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(orderInput)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderDetails() throws Exception {
        when(orderDetailServiceImpl.getOrderDetails()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/getOrderDetails"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrderDetails() throws Exception {
        when(orderDetailServiceImpl.getAllOrderDetails()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/getAllOrderDetails"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        
        return new ObjectMapper().writeValueAsString(obj);
    
}
}
