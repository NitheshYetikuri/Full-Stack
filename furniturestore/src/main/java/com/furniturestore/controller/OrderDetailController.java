package com.furniturestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furniturestore.entity.OrderDetail;
import com.furniturestore.entity.OrderInput;
import com.furniturestore.serviceImpl.OrderDetailServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderDetailController {
	
	@Autowired
	private OrderDetailServiceImpl orderDetailServiceImpl;
	
	
	
	@PreAuthorize("hasRole('User')")
	@PostMapping({"/placeOrder/{isSingleProductCheckout}"})
	public void placeOrder(@PathVariable(name= "isSingleProductCheckout") boolean isSingleProductCheckout, @RequestBody OrderInput orderInput) {
		log.info("Order Placed");
		orderDetailServiceImpl.placeOrder(orderInput, isSingleProductCheckout);
		
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping({"/getOrderDetails"})
	public List<OrderDetail> getOrderDetails() {
		log.info("Order details displayed");
		return orderDetailServiceImpl.getOrderDetails();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping({"/getAllOrderDetails"})
	public List<OrderDetail> getAllOrderDetails() {
		log.info("All Order details Displayed");
		return orderDetailServiceImpl.getAllOrderDetails();
	}

}
