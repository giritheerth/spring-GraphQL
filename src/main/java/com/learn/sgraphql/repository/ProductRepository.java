package com.learn.sgraphql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.sgraphql.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer> {

    List<Product> findByCategory(String category);
    

}
