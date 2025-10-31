package com.Laura.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallback(){
        return "ORDER SERVICE is DOWN now!!!!!!!!!";
   } 
}
