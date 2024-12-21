package com.learn.sgraphql.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityScan
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

private Integer id;
private String name;
private String category;
private String stock;
private String price;
}
