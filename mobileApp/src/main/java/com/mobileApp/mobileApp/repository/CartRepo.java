package com.mobileApp.mobileApp.repository;

import com.mobileApp.mobileApp.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,Long> {
    Optional<CartEntity> findByEmail(String email);
}
