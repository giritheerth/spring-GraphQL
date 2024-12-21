package com.learn.sgraphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learn.sgraphql.entity.Product;
import com.learn.sgraphql.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SgraphqlApplication {

	@Autowired
	private ProductRepository productRepository;

	/**@PostConstruct
	public void initDBEntries(){
			List<Product> products = new ArrayList<>();
			products.add(new Product(1, "Laptop", "Electronics", "10", "50000"));
			products.add(new Product(2, "Mobile", "Electronics", "20", "20000"));
			products.add(new Product(3, "Shirt", "Fashion", "100", "1000"));
			products.add(new Product(4, "Shoe", "Fashion", "50", "2000"));	
			productRepository.saveAll(products);
			System.out.println("Data Initialized");
	}**/

	public static void main(String[] args) {
		SpringApplication.run(SgraphqlApplication.class, args);
	}

}
