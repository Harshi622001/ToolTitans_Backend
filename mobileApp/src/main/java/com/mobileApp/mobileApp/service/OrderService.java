package com.mobileApp.mobileApp.service;

import com.mobileApp.mobileApp.entity.OrderEntity;
import com.mobileApp.mobileApp.DTO.OrderItemDTO;

import java.util.Date;
import java.util.List;

public interface OrderService {
    OrderEntity createOrder(String email, List<OrderItemDTO> items, Date deliveryDate, String address);
    List<OrderEntity> getOrdersByEmail(String email);
}