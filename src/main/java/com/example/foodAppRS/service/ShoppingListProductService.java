package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.dto.ShoppingListProductDTO;
import com.example.foodAppRS.entity.dto.ShoppingListProductMapper;
import com.example.foodAppRS.repository.ShoppingListProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListProductService {

    private final ShoppingListProductRepository shoppingListProductRepository;
    private final ShoppingListProductMapper shoppingListProductMapper;

    @Autowired
    public ShoppingListProductService(ShoppingListProductRepository shoppingListProductRepository,
                                      ShoppingListProductMapper shoppingListProductMapper) {
        this.shoppingListProductRepository = shoppingListProductRepository;
        this.shoppingListProductMapper = shoppingListProductMapper;
    }

    public List<ShoppingListProductDTO> selectAllShoppingList() {
        return shoppingListProductRepository.findAll()
                .stream()
                .map(shoppingListProductMapper)
                .toList();
    }
}
