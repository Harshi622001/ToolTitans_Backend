package com.mobileApp.mobileApp.controller;


import com.mobileApp.mobileApp.entity.ProductEntity;
import com.mobileApp.mobileApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ProductEntity> createProduct(
            @RequestParam(required = false) Long categoryId,  // Fixed typo
            @RequestBody ProductEntity product) {

        ProductEntity createdProduct = productService.createProduct(categoryId, product);
        return ResponseEntity.ok(createdProduct);
    }


    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id, @RequestBody ProductEntity product) {
        ProductEntity updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        ProductEntity product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/getProductsByCategoryId/{categoryId}")
    public ResponseEntity<List<ProductEntity>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductEntity> products = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }
}
