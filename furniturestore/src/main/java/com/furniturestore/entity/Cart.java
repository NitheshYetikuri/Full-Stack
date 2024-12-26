package com.furniturestore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	@OneToOne
	private Product product;
	@OneToOne
	private User user;
	
	
	
	public Cart() {
		super();
	}
	
	
	public Cart(Product product, User user) {
		super();
		this.product = product;
		this.user = user;
	}

}
