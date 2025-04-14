package com.mobileApp.mobileApp.serviceImpl;



import com.mobileApp.mobileApp.entity.CategoryEntity;
import com.mobileApp.mobileApp.repository.CategoryRepo;
import com.mobileApp.mobileApp.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepository;

    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        if (categoryRepository.existsByName(categoryEntity.getName())) {
            throw new IllegalArgumentException("Category with name " + categoryEntity.getName() + " already exists.");
        }
        return categoryRepository.save(categoryEntity);
    }


    @Override
    public CategoryEntity updateCategory(Long id, CategoryEntity category) {
        CategoryEntity existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
        existingCategory.setName(category.getName());
        existingCategory.setImage(category.getImage());
        return categoryRepository.save(category);
    }


    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
    }
}
