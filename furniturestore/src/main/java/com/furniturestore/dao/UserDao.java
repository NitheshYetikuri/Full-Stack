package com.furniturestore.dao;

import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;

import com.furniturestore.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
