package com.learn.sgraphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.learn.sgraphql.entity.Product;
import com.learn.sgraphql.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@RestController
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

   // @GetMapping("/products")  
   @QueryMapping("products")   
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }
   // @GetMapping("/products/{category}")
   @QueryMapping("productsByCategory")
    public List<Product> getProductByCategory(@Argument String category) {
        return productService.getProductByCategory(category);
    }   

}
