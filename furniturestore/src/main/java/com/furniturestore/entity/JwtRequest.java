package com.furniturestore.entity;

import lombok.Data;

@Data
public class JwtRequest {

   
	public JwtRequest() {
		// TODO Auto-generated constructor stub
	}
	private String userName;
    private String userPassword;

}
