package com.mobileApp.mobileApp.service;

import com.mobileApp.mobileApp.entity.CartEntity;
import com.mobileApp.mobileApp.entity.CartRespose;

public interface CartService {
    CartRespose getCart(String email);
    CartRespose addToCart(String email, Long productId, Double price);
    void decreaseQuantity(String email, Long productId);

}
