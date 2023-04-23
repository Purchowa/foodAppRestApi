package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.ShoppingListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListProductRepository extends JpaRepository<ShoppingListProduct, Integer> {
}
