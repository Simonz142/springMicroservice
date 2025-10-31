package com.example.OrderService.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderService.entity.Order;
import com.example.OrderService.extenral.client.ProductService;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.repository.OrderRepository;
import com.example.OrderService.service.OrderService;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        //order entity -> save the data inside orderDB
        //product service -> reduce stock
        //payment service -> pay?success -> complete, else throw error
        Order order = Order.builder()
                        .amount(orderRequest.getTotalAmount())
                        .orderStatus("CREATED")
                        .productId(orderRequest.getProductId())
                        .orderDate(Instant.now())
                        .quantity(orderRequest.getQuantity())
                        .build();


        //这行是干嘛呢
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());                
        order = orderRepository.save(order);
        return order.getId();
    }
    
}
