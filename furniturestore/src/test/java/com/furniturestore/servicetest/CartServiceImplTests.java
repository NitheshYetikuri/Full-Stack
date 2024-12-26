package com.furniturestore.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
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

import com.furniturestore.dao.CartDao;
import com.furniturestore.dao.ProductDao;
import com.furniturestore.dao.UserDao;
import com.furniturestore.entity.Cart;
import com.furniturestore.entity.Product;
import com.furniturestore.entity.User;
import com.furniturestore.serviceImpl.CartServiceImpl;

public class CartServiceImplTests {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartDao cartDao;

    @Mock
    private ProductDao productDao;

    @Mock
    private UserDao userDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteCartItem() {
        Integer cartId = 1;

        doNothing().when(cartDao).deleteById(cartId);

        cartService.deleteCartItem(cartId);

        verify(cartDao, times(1)).deleteById(cartId);
    }

    @Test
    public void testAddToCart() {
        Integer productId = 1;
        Product product = new Product();
        product.setProductId(productId);

        String username = "testUser";
        User user = new User();
        user.setUserName(username);

        List<Cart> cartList = new ArrayList<>();

        // This is to ensure that userDao.findById() inside addToCart() returns an Optional of user
        when(userDao.findById(anyString())).thenReturn(Optional.of(user));
        when(productDao.findById(productId)).thenReturn(Optional.of(product));
        when(cartDao.findByUser(user)).thenReturn(cartList);

        Cart savedCart = new Cart(product, user);
        when(cartDao.save(any(Cart.class))).thenReturn(savedCart);

        Cart cart = cartService.addToCart(productId);

        assertNotNull(cart);
        assertEquals(product, cart.getProduct());
        assertEquals(user, cart.getUser());
    }



    @Test
    public void testGetCartDetails() {
        String username = "testUser";
        User user = new User();
        user.setUserName(username);

        List<Cart> cartList = new ArrayList<>();
        Cart cart = new Cart();
        cart.setUser(user);
        cartList.add(cart);

        // This is to ensure that userDao.findById() inside getCartDetails() returns an Optional of user
        when(userDao.findById(anyString())).thenReturn(Optional.of(user));
        when(cartDao.findByUser(user)).thenReturn(cartList);

        List<Cart> returnedCartList = cartService.getCartDetails();

        assertEquals(1, returnedCartList.size());
        assertEquals(user, returnedCartList.get(0).getUser());
    }

}
