package com.mobileApp.mobileApp.repository;

import com.mobileApp.mobileApp.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItem,Long> {
}
