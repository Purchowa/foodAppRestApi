package com.example.foodAppRS.entity.dto;

import com.example.foodAppRS.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(product.getName());
    }
}
