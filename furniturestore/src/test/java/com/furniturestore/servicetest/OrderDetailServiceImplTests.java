package com.furniturestore.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.furniturestore.configuration.JwtRequestFilter;
import com.furniturestore.dao.CartDao;
import com.furniturestore.dao.OrderDetailDao;
import com.furniturestore.dao.ProductDao;
import com.furniturestore.dao.UserDao;
import com.furniturestore.entity.Cart;
import com.furniturestore.entity.OrderDetail;
import com.furniturestore.entity.OrderInput;
import com.furniturestore.entity.OrderProductQuantity;
import com.furniturestore.entity.Product;
import com.furniturestore.entity.User;
import com.furniturestore.serviceImpl.OrderDetailServiceImpl;

public class OrderDetailServiceImplTests {

    @InjectMocks
    private OrderDetailServiceImpl orderService;

    @Mock
    private OrderDetailDao orderDetailDao;

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
    public void testGetAllOrderDetails() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderFullName("Test User");
        orderDetails.add(orderDetail);

        when(orderDetailDao.findAll()).thenReturn(orderDetails);

        List<OrderDetail> returnedOrderDetails = orderService.getAllOrderDetails();

        assertEquals(1, returnedOrderDetails.size());
        assertEquals("Test User", returnedOrderDetails.get(0).getOrderFullName());
    }
    
    @Test
    public void testPlaceOrder() {
        String username = "testUser";
        User user = new User();
        user.setUserName(username);

        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductDiscountedPrice(100.0);

        Cart cart = new Cart();
        cart.setCartId(1);  // Set the cartId here
        cart.setUser(user);
        cart.setProduct(product);

        List<Cart> carts = new ArrayList<>();
        carts.add(cart);

        OrderInput orderInput = new OrderInput();
        OrderProductQuantity orderProductQuantity = new OrderProductQuantity();
        orderProductQuantity.setProductId(1);
        orderProductQuantity.setQuantity(1);
        List<OrderProductQuantity> orderProductQuantityList = new ArrayList<>();
        orderProductQuantityList.add(orderProductQuantity);
        orderInput.setOrderProductQuantityList(orderProductQuantityList);

        when(userDao.findById(username)).thenReturn(Optional.of(user));
        when(cartDao.findByUser(user)).thenReturn(carts);
        when(productDao.findById(1)).thenReturn(Optional.of(product));

        // Set the CURRENT_USER variable
        JwtRequestFilter.CURRENT_USER = username;

        orderService.placeOrder(orderInput, false);

        verify(orderDetailDao, times(1)).save(any(OrderDetail.class));
        verify(cartDao, times(1)).deleteById(anyInt());
    }




}
