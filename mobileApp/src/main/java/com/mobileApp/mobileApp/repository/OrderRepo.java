package com.mobileApp.mobileApp.repository;

import com.mobileApp.mobileApp.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByEmail(String email);
}