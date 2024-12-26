package com.furniturestore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    private String roleName;
    private String roleDescription;
    
    public Role() {
    	
    }

    public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
}
