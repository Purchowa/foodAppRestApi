package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findByNameIgnoreCase(String name);
}
