package com.learn.sgraphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.sgraphql.entity.Product;
import com.learn.sgraphql.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //get all product
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    //get product by category id
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }



}
