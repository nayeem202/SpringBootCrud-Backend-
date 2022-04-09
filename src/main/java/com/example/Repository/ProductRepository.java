package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
