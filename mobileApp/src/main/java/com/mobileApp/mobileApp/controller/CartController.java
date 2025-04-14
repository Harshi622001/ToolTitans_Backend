package com.mobileApp.mobileApp.controller;

import com.mobileApp.mobileApp.entity.CartEntity;
import com.mobileApp.mobileApp.entity.CartRespose;
import com.mobileApp.mobileApp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {
@Autowired
    private  CartService cartService;

    @GetMapping("/getcart")
    public ResponseEntity<CartRespose> getCart(@RequestParam String email) {
        CartRespose cart = cartService.getCart(email);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/addtocart")
    public ResponseEntity<CartRespose> addToCart(@RequestParam String email,
                                                 @RequestParam Long productId,
                                                 @RequestParam Double price
    ) {
        CartRespose cartRespose = cartService.addToCart(email, productId, price);
        return ResponseEntity.ok(cartRespose);
    }
    @PostMapping("/decrease")
    public ResponseEntity<String> decreaseQuantity(@RequestParam String email,
                                                   @RequestParam Long productId) {
        try {
            cartService.decreaseQuantity(email, productId);
            return ResponseEntity.ok("Product quantity decreased successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
