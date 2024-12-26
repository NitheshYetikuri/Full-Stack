package com.furniturestore.service;

import java.util.List;

import com.furniturestore.entity.OrderDetail;
import com.furniturestore.entity.OrderInput;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetails();
    List<OrderDetail> getOrderDetails();
    void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout);
}
