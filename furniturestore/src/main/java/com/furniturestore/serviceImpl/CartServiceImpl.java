package com.furniturestore.serviceImpl;

import java.util.List;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furniturestore.configuration.JwtRequestFilter;
import com.furniturestore.dao.CartDao;
import com.furniturestore.dao.ProductDao;
import com.furniturestore.dao.UserDao;
import com.furniturestore.entity.Cart;
import com.furniturestore.entity.Product;
import com.furniturestore.entity.User;
import com.furniturestore.exception.ProductNotFoundException;
import com.furniturestore.exception.UserNotFoundException;

@Service
public class CartServiceImpl {
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	public void deleteCartItem(Integer cartId) {
		cartDao.deleteById(cartId);
	}
	
	public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId)
                                    .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username)
                          .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        List<Cart> cartList = cartDao.findByUser(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());

        if (filteredList.size() > 0) {
            // Handle duplicate cart item
            return null;
        }

        Cart cart = new Cart(product, user);
        return cartDao.save(cart);
    }
	
	public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username)
                          .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return cartDao.findByUser(user);
    }
	
	

}
