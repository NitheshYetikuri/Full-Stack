package com.furniturestore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	@Column(length = 1000)
	private String productDescription;
	private Double productDiscountedPrice;
	private Double productActualPrice;
	
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
	@JoinTable(name = "product_images",
	joinColumns = {
			@JoinColumn(name = "product_id")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "image_id")
	})
	private Set<ImageModel> productImages;
	


	//private String productImages;
	

	public Product() {
	
	}

	public Product(Integer productId, String productName, String productDescription, Double productDiscountedPrice,
			Double productActualPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productDiscountedPrice = productDiscountedPrice;
		this.productActualPrice = productActualPrice;
	}

}
