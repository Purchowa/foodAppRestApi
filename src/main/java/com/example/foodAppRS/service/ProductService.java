package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.Product;
import com.example.foodAppRS.entity.dto.ProductDTO;
import com.example.foodAppRS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findByNameIgnoreCase(String productName) {
        return productRepository.findByNameIgnoreCase(productName);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public ProductDTO addProductToFridge(Product product) {
        Product toSave = new Product();
        toSave.setId(product.getId());
        toSave.setName(product.getName());
        toSave.setFridgeList(product.getFridgeList());

        productRepository.save(toSave);

        return new ProductDTO(toSave.getName());
    }
}
