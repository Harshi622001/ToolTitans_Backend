package com.mobileApp.mobileApp.repository;

import com.mobileApp.mobileApp.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByIdIn(List<Long> ids);

    List<ProductEntity> findByCategoryId(Long categoryId);
}
