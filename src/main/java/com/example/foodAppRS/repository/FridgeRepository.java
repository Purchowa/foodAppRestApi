package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FridgeRepository extends JpaRepository<Fridge, Integer> {
    public List<Fridge> findFridgeByAccount_Username(String username);
}
