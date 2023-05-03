package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.ShoppingListProduct;
import com.example.foodAppRS.entity.dto.ShoppingListProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListProductRepository extends JpaRepository<ShoppingListProduct, Integer> {
    List<ShoppingListProduct> findShoppingListProductByAccount_Username(String username);
}
