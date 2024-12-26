package com.furniturestore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.furniturestore.entity.OrderDetail;
import com.furniturestore.entity.User;

@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer>{
	
	public List<OrderDetail> findByUser(User user);

}
