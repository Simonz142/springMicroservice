package com.example.OrderService.service;

import com.example.OrderService.model.OrderRequest;

public interface OrderService {

    Long placeOrder(OrderRequest orderRequest);
    
}
