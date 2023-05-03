package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Product;
import com.example.foodAppRS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository pRep){
        this.repository = pRep;
    }

    @GetMapping("product")
    public List<Product> getAllProducts(){
        return repository.findAll();
    }
}
