//package com.mobileApp.mobileApp.serviceImpl;
//
//import com.mobileApp.mobileApp.entity.SubcategoryEntity;
//import com.mobileApp.mobileApp.repository.SubCategoryRepo;
//import com.mobileApp.mobileApp.service.SubcategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SubcategoryServiceImpl implements SubcategoryService {
//
//    @Autowired
//    private SubCategoryRepo subcategoryRepository;
//
//    @Override
//    public SubcategoryEntity createSubcategory(Long categoryId, SubcategoryEntity subcategory) {
//        // Set the categoryId manually (no mapping is used)
//        subcategory.setCategoryId(categoryId);
//        return subcategoryRepository.save(subcategory);
//    }
//
//    @Override
//    public SubcategoryEntity updateSubcategory(Long id, SubcategoryEntity subcategory) {
//        // Fetch the existing subcategory
//        SubcategoryEntity existingSubcategory = subcategoryRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Subcategory not found with ID: " + id));
//
//        // Update the fields
//        existingSubcategory.setName(subcategory.getName());
//        existingSubcategory.setImage(subcategory.getImage());
//        existingSubcategory.setCategoryId(subcategory.getCategoryId()); // Update categoryId if needed
//
//        return subcategoryRepository.save(existingSubcategory);
//    }
//
//    @Override
//    public void deleteSubcategory(Long id) {
//        if (!subcategoryRepository.existsById(id)) {
//            throw new IllegalArgumentException("Subcategory not found with ID: " + id);
//        }
//        subcategoryRepository.deleteById(id);
//    }
//
//    @Override
//    public List<SubcategoryEntity> getAllSubcategories() {
//        return subcategoryRepository.findAll();
//    }
//@Override
//    public List<SubcategoryEntity> getSubcategoriesByCategoryId(Long categoryId) {
//        return subcategoryRepository.findByCategoryId(categoryId);
//    }
//
//}
