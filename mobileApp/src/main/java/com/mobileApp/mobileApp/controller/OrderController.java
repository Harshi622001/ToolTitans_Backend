package com.mobileApp.mobileApp.controller;

import com.mobileApp.mobileApp.DTO.OrderItemDTO;
import com.mobileApp.mobileApp.entity.OrderEntity;
import com.mobileApp.mobileApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderEntity> createOrder(
            @RequestParam String email,
            @RequestParam String address,
            @RequestBody List<OrderItemDTO> items) {
        try {
            LocalDateTime twoDaysAfterNow = LocalDateTime.now().plusDays(2);

            // Convert to Date (if needed)
            Date deliveryDate = Date.from(twoDaysAfterNow.atZone(ZoneId.systemDefault()).toInstant());
            OrderEntity order = orderService.createOrder(email, items, deliveryDate, address);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/orders/{email}")
    public ResponseEntity<List<OrderEntity>> getOrdersByEmail(@PathVariable String email) {
        List<OrderEntity> orders = orderService.getOrdersByEmail(email);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}