package com.mobileApp.mobileApp.serviceImpl;

import com.mobileApp.mobileApp.entity.ProductDetails;
import com.mobileApp.mobileApp.entity.ProductEntity;
import com.mobileApp.mobileApp.repository.CategoryRepo;
import com.mobileApp.mobileApp.repository.ProductRepo;
import com.mobileApp.mobileApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    @Override
    public ProductEntity createProduct(Long categoryId, ProductEntity product) {
        if (categoryId != null) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }

        if (product.getProductDetails() == null || product.getProductDetails().isEmpty()) {
            throw new IllegalArgumentException("At least one product variant must be provided.");
        }

        for (ProductDetails detail : product.getProductDetails()) {
            if (detail.getVariant() == null || detail.getVariant().isEmpty()) {
                throw new IllegalArgumentException("Each product variant must have a name.");
            }
            if (detail.getPrice() == null || detail.getPrice() <= 0) {
                throw new IllegalArgumentException("Variant price must be greater than zero.");
            }

            if (detail.getDescription() == null) {
                detail.setDescription(new HashMap<>());
            }

            detail.setProduct(product); // Link variant to product
        }

        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity product) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        if (product.getName() != null && !product.getName().isEmpty()) {
            existingProduct.setName(product.getName());
        }
        if (product.getImage() != null) {
            existingProduct.setImage(product.getImage());
        }
        if (product.getNoOfUnitsInBox() != null) {
            existingProduct.setNoOfUnitsInBox(product.getNoOfUnitsInBox());
        }
        if (product.getBrand() != null && !product.getBrand().isEmpty()) {
            existingProduct.setBrand(product.getBrand());
        }

        if (product.getProductDetails() != null && !product.getProductDetails().isEmpty()) {
            existingProduct.getProductDetails().clear();

            for (ProductDetails detail : product.getProductDetails()) {
                if (detail.getVariant() == null || detail.getVariant().isEmpty()) {
                    throw new IllegalArgumentException("Each product variant must have a name.");
                }
                if (detail.getPrice() == null || detail.getPrice() <= 0) {
                    throw new IllegalArgumentException("Variant price must be greater than zero.");
                }

                if (detail.getDescription() == null) {
                    detail.setDescription(new HashMap<>());
                }

                detail.setProduct(existingProduct);
                existingProduct.getProductDetails().add(detail);
            }
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        // Ensure 'productDetails' collection is initialized before returning
        products.forEach(product -> product.getProductDetails().size());
        return products;
    }

    @Override
    @Transactional
    public ProductEntity getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        // Initialize the 'productDetails' collection to avoid lazy loading issues
        product.getProductDetails().size();

        return product;
    }

    @Override
    @Transactional
    public List<ProductEntity> getProductsByCategoryId(Long categoryId) {
        List<ProductEntity> products = productRepository.findByCategoryId(categoryId);
        // Ensure 'productDetails' collection is initialized before returning
        products.forEach(product -> product.getProductDetails().size());
        return products;
    }
}
