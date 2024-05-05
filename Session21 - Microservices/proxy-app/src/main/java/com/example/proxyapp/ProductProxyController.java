package com.example.proxyapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.proxyapp.client.ProductFeignClient;
import com.example.proxyapp.dto.ProductDto;

@RestController
@RequestMapping("/proxy")
public class ProductProxyController {

    private static Logger logger = LoggerFactory.getLogger(ProductProxyController.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${product.app.url}")
    private String productAppUrl;

    @Autowired
    ProductFeignClient productFeignClient;

    //Rest Template Approach
//    @PostMapping("/product")
//    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
//        logger.info("In Proxy-app, ProductController.saveProduct with productDto: {}",productDto);
//
//        String url = productAppUrl + "product";
//        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity(url,productDto, ProductDto.class);
//        return responseEntity;
//    }
//
//    @GetMapping("product/{id}")
//    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
//        logger.info("In Proxy-app, ProductController.getProductById with id: {}",id);
//
//        String url = productAppUrl + "product/" + id;
//        ProductDto productDto = restTemplate.getForObject(url,ProductDto.class);
//        return ResponseEntity.ok(productDto);
//    }

    // Feign Client
    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        logger.info("In Proxy-app, ProductController.saveProduct with productDto: {}",productDto);
        return ResponseEntity.ok(productFeignClient.saveProduct(productDto));
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        logger.info("In Proxy-app, ProductController.getProductById with id: {}",id);
        return ResponseEntity.ok(productFeignClient.getProductById(id));
    }


}
