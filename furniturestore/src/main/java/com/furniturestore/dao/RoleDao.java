package com.furniturestore.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.furniturestore.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
