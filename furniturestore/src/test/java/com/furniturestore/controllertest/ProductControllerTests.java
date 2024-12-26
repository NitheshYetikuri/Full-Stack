package com.furniturestore.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.furniturestore.controller.ProductController;
import com.furniturestore.entity.Product;
import com.furniturestore.serviceImpl.ProductServiceImpl;

@SpringBootTest
public class ProductControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ProductServiceImpl productServiceImpl;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testAddNewProduct() throws Exception {
        Product product = new Product();
        // Set product properties
        mockMvc.perform(post("/addNewProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        when(productServiceImpl.getAllProducts(0, "")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/getAllProducts"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProductDetailsById() throws Exception {
        Product product = new Product();
        // Set product properties
        when(productServiceImpl.getProductDetailsById(1)).thenReturn(product);
        mockMvc.perform(get("/getProductDetailsById/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProductDetails() throws Exception {
        mockMvc.perform(delete("/deleteProductDetails/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProductDetails() throws Exception {
        when(productServiceImpl.getProductDetails(true, 1)).thenReturn(Collections.singletonList(new Product()));
        mockMvc.perform(get("/getProductDetails/true/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        
        return new ObjectMapper().writeValueAsString(obj);
    
}
}
