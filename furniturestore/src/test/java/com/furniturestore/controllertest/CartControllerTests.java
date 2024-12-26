package com.furniturestore.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.furniturestore.controller.CartController;
import com.furniturestore.entity.Cart;
import com.furniturestore.serviceImpl.CartServiceImpl;

@SpringBootTest
public class CartControllerTests {

    private MockMvc mockMvc;

    @Mock
    private CartServiceImpl cartServiceImpl;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testAddToCart() throws Exception {
        Cart cart = new Cart();
        // Set cart properties
        when(cartServiceImpl.addToCart(1)).thenReturn(cart);
        mockMvc.perform(get("/addToCart/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCartItem() throws Exception {
        mockMvc.perform(delete("/deleteCartItem/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCartDetails() throws Exception {
        when(cartServiceImpl.getCartDetails()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/getCartDetails"))
                .andExpect(status().isOk());
    }
}
