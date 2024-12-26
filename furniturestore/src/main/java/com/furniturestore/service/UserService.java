package com.furniturestore.service;

import com.furniturestore.entity.User;

public interface UserService {
    void initRoleAndUser();
    User registerNewUser(User user);
    String getEncodedPassword(String password);
}