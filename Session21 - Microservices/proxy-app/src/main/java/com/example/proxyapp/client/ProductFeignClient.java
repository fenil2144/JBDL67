package com.example.proxyapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.proxyapp.dto.ProductDto;

@FeignClient(name = "PRODUCT-APP")
//@FeignClient(name = "product-app-client", url = "${product.app.url}")
public interface ProductFeignClient {

    @PostMapping("/product")
    ProductDto saveProduct(@RequestBody ProductDto productDto);

    @GetMapping("/product/{id}")
    ProductDto getProductById(@PathVariable Long id);
}
