package com.learn.sgraphql.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.learn.sgraphql.entity.Product;

@GraphQlTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private GraphQlTester tester;

    @MockBean
    

    @Test
    void testGetAllProductsShouldReturnAllProducts() {
        // Test the getAllProducts query
        String document = """
                                query MyQuery {
                  products {
                    id
                    name
                    price
                    stock
                  }
                }
                                """;
        // Execute the query
        tester.document(document)
                .execute()
                .path("products")
                .entityList(Product.class)
                .hasSizeGreaterThan(0);
    }

}
