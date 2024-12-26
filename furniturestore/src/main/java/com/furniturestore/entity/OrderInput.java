package com.furniturestore.entity;

import java.util.List;

import lombok.Data;

@Data
public class OrderInput {
	
	private String fullName;
	private String fullAddress;
	private String contactNumber;
	private String alternateContactNumber;
	private List<OrderProductQuantity> orderProductQuantityList;
	
	

	public OrderInput() {
		super();
	}

}