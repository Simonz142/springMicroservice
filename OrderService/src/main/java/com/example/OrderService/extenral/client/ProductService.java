package com.example.OrderService.extenral.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "PRODUCT-SERVICE")// PRODUCT_SERVICE指的是yaml里起的名字，这个app就叫PRODUCT_SERVICE， 后面的是controller的路径
public interface ProductService {
    @PutMapping("/product/reduceQuantity/{id}") //到时候url 就这么写，里面带 quantity的，PUT /reduceQuantity/5?quantity=3
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam  long quantity);
}
