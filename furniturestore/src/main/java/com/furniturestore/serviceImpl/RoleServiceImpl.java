package com.furniturestore.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.furniturestore.dao.RoleDao;
import com.furniturestore.entity.Role;
import com.furniturestore.exception.RoleCreationException;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        try {
            return roleDao.save(role);
        } catch (Exception e) {
            throw new RoleCreationException("Failed to create new role: " + e.getMessage());
        }
    }
}
