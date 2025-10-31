package com.laura.ProductService.service;

import com.laura.ProductService.model.ProductRequest;
import com.laura.ProductService.model.ProductResponse;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
    
}
