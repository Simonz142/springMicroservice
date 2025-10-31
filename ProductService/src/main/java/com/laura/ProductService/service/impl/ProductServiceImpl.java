package com.laura.ProductService.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.ProductService.entity.Product;
import com.laura.ProductService.exception.ProductServiceCustomException;
import com.laura.ProductService.model.ProductRequest;
import com.laura.ProductService.model.ProductResponse;
import com.laura.ProductService.repository.ProductRepository;
import com.laura.ProductService.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2 // this annotation shows logs
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        //this is frontend request, json format,frontend json message deliver to backend Entity, and then save entity to database
        log.info("Add product....");

        Product product = Product.builder()
                        .productName(productRequest.getName())
                        .price(productRequest.getPrice())
                        .quantity(productRequest.getQuantity())
                        .build();

        productRepository.save(product);//this method comes from jpaRepository
        log.info("product is created....");
        return product.getProductId();

    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for id: {}", productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException("product with given id not found", "PRODUCT_NOT_FOUND"));//here use lambda
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);// I got a product object here, and then pass info to productResponse
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("reduce stock {} for id {}", quantity,productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND" ));
        if(product.getQuantity() < quantity){
            throw new ProductServiceCustomException("product does not have sufficient  quantity", "INSUFFICIENT");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity updated successfully");
    }

    
}
