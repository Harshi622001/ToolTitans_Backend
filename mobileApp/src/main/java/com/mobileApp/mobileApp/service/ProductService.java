package com.mobileApp.mobileApp.service;


import com.mobileApp.mobileApp.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity createProduct(Long categoryId, ProductEntity product);
    ProductEntity updateProduct(Long id, ProductEntity product);
    void deleteProduct(Long id);
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    List<ProductEntity> getProductsByCategoryId(Long categoryId);
}
