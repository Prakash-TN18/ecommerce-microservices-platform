package com.prakash.orderservice.service;

import com.prakash.orderservice.client.ProductClient;
import com.prakash.orderservice.dto.ProductDto;
import com.prakash.orderservice.entity.OrderEntity;
import com.prakash.orderservice.repository.OrderRepository;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public OrderService(OrderRepository orderRepository,
                        ProductClient productClient,
                        CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public OrderEntity placeOrder(OrderEntity order) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("productService");

        ProductDto product = circuitBreaker.run(
                () -> productClient.getProductById(order.getProductId()),
                throwable -> fallbackProduct(order.getProductId(), throwable)
        );

        if (product == null) {
            order.setStatus("PENDING - PRODUCT SERVICE DOWN");
            return orderRepository.save(order);
        }

        if (product.getStockQuantity() == null || product.getStockQuantity() < order.getQuantity()) {
            order.setStatus("FAILED - INSUFFICIENT STOCK");
            return orderRepository.save(order);
        }

        order.setStatus("PLACED");
        return orderRepository.save(order);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    private ProductDto fallbackProduct(Long productId, Throwable throwable) {
        System.out.println("Fallback triggered for productId: " + productId + ", reason: " + throwable.getMessage());
        return null;
    }
}