package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.dto.ShoppingListProductDTO;
import com.example.foodAppRS.service.ShoppingListProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingListController {
    private final ShoppingListProductService shoppingListProductService;

    @Autowired
    public ShoppingListController(ShoppingListProductService shoppingListProductService) {
        this.shoppingListProductService = shoppingListProductService;
    }

    @GetMapping("/shoppingList")
    public List<ShoppingListProductDTO> selectAllShoppingList() {
        return shoppingListProductService.selectAllShoppingList();
    }
}
