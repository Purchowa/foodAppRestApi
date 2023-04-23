package com.example.foodAppRS.entity.dto;

import com.example.foodAppRS.entity.ShoppingListProduct;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ShoppingListProductMapper implements Function<ShoppingListProduct, ShoppingListProductDTO> {
    @Override
    public ShoppingListProductDTO apply(ShoppingListProduct shoppingListProduct) {
        return new ShoppingListProductDTO(shoppingListProduct.getQuantity());
    }
}
