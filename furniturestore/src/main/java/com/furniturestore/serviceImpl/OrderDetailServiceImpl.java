package com.furniturestore.serviceImpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furniturestore.configuration.JwtRequestFilter;
import com.furniturestore.dao.CartDao;
import com.furniturestore.dao.OrderDetailDao;
import com.furniturestore.dao.ProductDao;
import com.furniturestore.dao.UserDao;
import com.furniturestore.entity.Cart;
import com.furniturestore.entity.OrderDetail;
import com.furniturestore.entity.OrderInput;
import com.furniturestore.entity.OrderProductQuantity;
import com.furniturestore.entity.Product;
import com.furniturestore.entity.User;
import com.furniturestore.exception.ProductNotFoundException;
import com.furniturestore.exception.UserNotFoundException;

@Service
public class OrderDetailServiceImpl {
	
	private static final String ORDER_PLACED = "Placed";  
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	
	public List<OrderDetail> getAllOrderDetails(){
		List<OrderDetail> orderDetails = new ArrayList<>();
		orderDetailDao.findAll().forEach(e -> orderDetails.add(e));
		
		return orderDetails;
	}
	
	public List<OrderDetail> getOrderDetails() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser)
                           .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + currentUser));
        
        return orderDetailDao.findByUser(user);
    }
	
	public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
		List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
		
		for(OrderProductQuantity o: productQuantityList) {
			Product product = productDao.findById(o.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + o.getProductId()));
			
			String currentUser = JwtRequestFilter.CURRENT_USER;
			User user= userDao.findById(currentUser).get();
			
			OrderDetail orderDetail = new OrderDetail(
					orderInput.getFullName(),
					orderInput.getFullAddress(),
					orderInput.getContactNumber(),
					orderInput.getAlternateContactNumber(),
					ORDER_PLACED,
					product.getProductDiscountedPrice()*o.getQuantity(),
					product,
					user);
			
			if(!isSingleProductCheckout) {
				List<Cart> carts= cartDao.findByUser(user);
				carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));			
				
			}
			orderDetailDao.save(orderDetail);
		}
	}
	
	

}
