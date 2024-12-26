package com.furniturestore.service;

import java.util.List;

import com.furniturestore.entity.Cart;

public interface CartService {
    void deleteCartItem(Integer cartId);
    Cart addToCart(Integer productId);
    List<Cart> getCartDetails();
}
