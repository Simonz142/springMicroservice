package com.laura.ProductService.model;

import lombok.Data;

@Data //this is from lombok
public class ProductRequest {
    private String name;
    private long price;
    private long quantity;
}
