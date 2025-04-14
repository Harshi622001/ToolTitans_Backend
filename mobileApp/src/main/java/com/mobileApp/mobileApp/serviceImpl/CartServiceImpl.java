package com.mobileApp.mobileApp.serviceImpl;

import com.mobileApp.mobileApp.entity.*;
import com.mobileApp.mobileApp.repository.CartItemRepository;
import com.mobileApp.mobileApp.repository.CartRepo;
import com.mobileApp.mobileApp.repository.ProductRepo;
import com.mobileApp.mobileApp.repository.UserRepo;
import com.mobileApp.mobileApp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepository;
    @Autowired
    private ProductRepo productRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private CartItemRepository cartItemRepo;

    @Override
    public CartRespose getCart(String email) {
        try {
            CartEntity cart = cartRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Cart not found for user"));
            CartRespose response = new CartRespose();
            List<CartProductModel> products = cart.getItems().stream().map(item -> {
                ProductEntity product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                return new CartProductModel(product, item.getQuantity());
            }).collect(Collectors.toList());
            response.setProducts(products);
            response.setTotal(cart.getItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving cart: " + e.getMessage(), e);
        }
    }

    @Override
    public CartRespose addToCart(String email, Long productId, Double price) {
        try {
            Optional<CartEntity> cartOptional = cartRepository.findByEmail(email);

            CartEntity cart;
            if (cartOptional.isPresent()) {
                cart = cartOptional.get();
            } else {
                cart = new CartEntity();
                cart.setEmail(email);
                cartRepository.save(cart);
            }

            List<CartItem> cartItems = cart.getItems();
            boolean productExists = false;
            for (CartItem item : cartItems) {
                if (item.getProductId().equals(productId)) {
                    item.setQuantity(item.getQuantity() + 1);
                    productExists = true;
                    break;
                }
            }
            if (!productExists) {
                CartItem item = new CartItem();
                item.setProductId(productId);
                item.setPrice(price);
                item.setQuantity(1);
                cart.addItem(item);
            }

            cartRepository.save(cart);

            List<CartProductModel> products = cart.getItems().stream().map(item -> {
                ProductEntity product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                return new CartProductModel(product, item.getQuantity());
            }).collect(Collectors.toList());
            CartRespose response = new CartRespose();
            response.setProducts(products);
            response.setTotal(cart.getItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error adding to cart: " + e.getMessage(), e);
        }
    }

    @Override
    public void decreaseQuantity(String email, Long productId) {
        try {
            CartEntity cart = cartRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Cart not found for user"));

            List<CartItem> cartItems = cart.getItems();
            for (CartItem item : cartItems) {
                if (item.getProductId().equals(productId)) {
                    int newQuantity = item.getQuantity() - 1;
                    if (newQuantity > 0) {
                        item.setQuantity(newQuantity);
                    } else {
                        cartItems.remove(item);
                    }
                    break;
                }
            }

            cartRepository.save(cart);
        } catch (Exception e) {
            throw new RuntimeException("Error decreasing quantity: " + e.getMessage(), e);
        }
    }
}


