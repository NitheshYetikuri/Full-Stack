package com.furniturestore.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.furniturestore.configuration.JwtRequestFilter;
import com.furniturestore.dao.CartDao;
import com.furniturestore.dao.ProductDao;
import com.furniturestore.dao.UserDao;
import com.furniturestore.entity.Cart;
import com.furniturestore.entity.Product;
import com.furniturestore.entity.User;
import com.furniturestore.exception.ProductAdditionException;
import com.furniturestore.serviceImpl.ProductServiceImpl;

public class ProductServiceImplTests {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDao productDao;

    @Mock
    private UserDao userDao;

    @Mock
    private CartDao cartDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddNewProduct() {
        // Create a mock ProductDao
        ProductDao productDao = mock(ProductDao.class);

        // Create a product to add
        Product product = new Product(1, "Test Product", "This is a test product", 50.0, 100.0);

        // When productDao.save is called with the product, return the product
        when(productDao.save(product)).thenReturn(product);

        // Create a ProductService and inject the mocked ProductDao
        ProductServiceImpl productService = new ProductServiceImpl(productDao);

        // Call addNewProduct
        Product returnedProduct = productService.addNewProduct(product);

        // Verify that productDao.save was called
        verify(productDao, times(1)).save(product);

        // Assert that the returned product is the same as the one we created
        assertEquals(product, returnedProduct);
    }

    @Test
    public void testAddNewProductThrowsException() {
        // Create a mock ProductDao
        ProductDao productDao = mock(ProductDao.class);

        // Create a product to add
        Product product = new Product(1, "Test Product", "This is a test product", 50.0, 100.0);

        // When productDao.save is called with the product, throw an exception
        when(productDao.save(product)).thenThrow(new RuntimeException("Database error"));

        // Create a ProductService and inject the mocked ProductDao
        ProductServiceImpl productService = new ProductServiceImpl(productDao);

        // Assert that an exception of type ProductAdditionException is thrown
        assertThrows(ProductAdditionException.class, () -> productService.addNewProduct(product));
    }

    

    @Test
    public void testGetAllProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductName("Test Product");
        products.add(product);

        Pageable pageable = PageRequest.of(0, 8);

        when(productDao.findAll(pageable)).thenReturn(products);

        List<Product> returnedProducts = productService.getAllProducts(0, "");

        assertEquals(1, returnedProducts.size());
        assertEquals("Test Product", returnedProducts.get(0).getProductName());
    }
    
    @Test
    public void testDeleteProductDetails() {
        Integer productId = 1;

        doNothing().when(productDao).deleteById(productId);

        productService.deleteProductDetails(productId);

        verify(productDao, times(1)).deleteById(productId);
    }

    @Test
    public void testGetProductDetailsById() {
        Integer productId = 1;
        Product product = new Product();
        product.setProductName("Test Product");

        when(productDao.findById(productId)).thenReturn(Optional.of(product));

        Product returnedProduct = productService.getProductDetailsById(productId);

        assertEquals("Test Product", returnedProduct.getProductName());
    }

    @Test
    public void testGetProductDetails() {
        String username = "testUser";
        User user = new User();
        user.setUserName(username);

        Product product = new Product();
        product.setProductName("Test Product");

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);

        List<Cart> carts = new ArrayList<>();
        carts.add(cart);

        when(userDao.findById(username)).thenReturn(Optional.of(user));
        when(cartDao.findByUser(user)).thenReturn(carts);

        // Set the CURRENT_USER variable
        JwtRequestFilter.CURRENT_USER = username;

//        List<Product> returnedProducts = productService.getProductDetails(false, 0);
//
//        assertEquals(1, returnedProducts.size());
//        assertEquals("Test Product", returnedProducts.get(0).getProductName());
    }



    
}
