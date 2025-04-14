//package com.mobileApp.mobileApp.controller;
//
//import com.mobileApp.mobileApp.entity.SubcategoryEntity;
//import com.mobileApp.mobileApp.service.SubcategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/subcategories")
//@CrossOrigin(origins = "*")
//public class SubcategoryController {
//
//    @Autowired
//    private SubcategoryService subcategoryService;
//
//    @PostMapping("/{categoryId}")
//    public ResponseEntity<SubcategoryEntity> createSubcategory(@PathVariable Long categoryId, @RequestBody SubcategoryEntity subcategory) {
//        SubcategoryEntity createdSubcategory = subcategoryService.createSubcategory(categoryId, subcategory);
//        return ResponseEntity.ok(createdSubcategory);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<SubcategoryEntity> updateSubcategory(@PathVariable Long id, @RequestBody SubcategoryEntity subcategory) {
//        SubcategoryEntity updatedSubcategory = subcategoryService.updateSubcategory(id, subcategory);
//        return ResponseEntity.ok(updatedSubcategory);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteSubcategory(@PathVariable Long id) {
//        subcategoryService.deleteSubcategory(id);
//        return ResponseEntity.ok("Subcategory deleted successfully.");
//    }
//
//    @GetMapping
//    public ResponseEntity<List<SubcategoryEntity>> getAllSubcategories() {
//        List<SubcategoryEntity> subcategories = subcategoryService.getAllSubcategories();
//        return ResponseEntity.ok(subcategories);
//    }
//
//    @GetMapping("/fetchByCategoryId/{categoryId}")
//    public ResponseEntity<List<SubcategoryEntity>> getSubcategoriesByCategoryId(@PathVariable Long categoryId) {
//        List<SubcategoryEntity> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);
//        return ResponseEntity.ok(subcategories);
//    }
//}
