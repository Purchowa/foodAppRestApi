package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.Product;
import com.example.foodAppRS.entity.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findByNameIgnoreCase(String name);


    @Query(value = "FROM Product WHERE name = :name")
    ProductDTO findByName(String name);
}
