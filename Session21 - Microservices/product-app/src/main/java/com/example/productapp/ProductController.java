package com.example.productapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id){
        logger.info("In Product-app, ProductController.getProductById with id: {}",id);
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    ResponseEntity<Product> saveProduct(@RequestBody Product product){
        logger.info("In Product-app, ProductController.saveProduct with product: {}",product);
        return ResponseEntity.ok(productService.saveProduct(product));
    }
}
