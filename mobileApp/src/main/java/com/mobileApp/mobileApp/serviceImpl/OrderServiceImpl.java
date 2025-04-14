package com.mobileApp.mobileApp.serviceImpl;

import com.mobileApp.mobileApp.DTO.OrderItemDTO;
import com.mobileApp.mobileApp.entity.*;
import com.mobileApp.mobileApp.repository.CartRepo;
import com.mobileApp.mobileApp.repository.OrderRepo;
import com.mobileApp.mobileApp.repository.ProductRepo;
import com.mobileApp.mobileApp.service.CartService;
import com.mobileApp.mobileApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepository;

    @Override
    public OrderEntity createOrder(String email, List<OrderItemDTO> items, Date deliveryDate, String address) {
        double totalPrice = 0;

        // Convert DTOs to entities
        List<OrderItemEntity> orderItems = items.stream().map(itemDTO -> {
            ProductEntity product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Find the correct variant (ProductDetails)
            ProductDetails variantDetail = product.getProductDetails().stream()
                    .filter(detail -> detail.getVariant().equalsIgnoreCase(itemDTO.getVariant()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product variant not found"));

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProductId(product.getId());
            orderItem.setVariant(itemDTO.getVariant());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(variantDetail.getPrice() * itemDTO.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());

        // Calculate total price
        for (OrderItemEntity item : orderItems) {
            totalPrice += item.getPrice();
        }

        // Create order
        OrderEntity order = new OrderEntity();
        order.setEmail(email);
        order.setItems(orderItems);
        order.setOrderDate(new Date());
        order.setDeliveryDate(deliveryDate);
        order.setTotalPrice(totalPrice);
        order.setAddress(address);
        order.setStatus("Processing");

        OrderEntity savedOrder = orderRepository.save(order);
        if (savedOrder == null) {
            throw new RuntimeException("Failed to create order");
        } else {
            cartRepo.findByEmail(email).ifPresent(cart -> {
                cart.setItems(null);
                cartRepo.save(cart);
            });
            return savedOrder;
        }
    }

    @Override
    public List<OrderEntity> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }
}