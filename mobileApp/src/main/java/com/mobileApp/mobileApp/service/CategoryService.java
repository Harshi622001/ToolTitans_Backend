package com.mobileApp.mobileApp.service;

import com.mobileApp.mobileApp.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity createCategory(CategoryEntity category);
    CategoryEntity updateCategory(Long id, CategoryEntity category);
    void deleteCategory(Long id);
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(Long id);
}
