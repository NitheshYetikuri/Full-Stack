package com.furniturestore.service;

import java.util.List;

import com.furniturestore.entity.Product;

public interface ProductService {
    Product addNewProduct(Product product);
    List<Product> getAllProducts(int pageNumber, String searchKey);
    void deleteProductDetails(Integer productId);
    Product getProductDetailsById(Integer productId);
    List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId);
}