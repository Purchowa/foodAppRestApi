package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.entity.Product;
import com.example.foodAppRS.entity.ShoppingListProduct;
import com.example.foodAppRS.entity.dto.FridgeDTO;
import com.example.foodAppRS.entity.dto.ShoppingListProductDTO;
import com.example.foodAppRS.entity.dto.ShoppingListProductMapper;
import com.example.foodAppRS.exception.type.ResourceNotFoundException;
import com.example.foodAppRS.repository.AccountRepository;
import com.example.foodAppRS.repository.ProductRepository;
import com.example.foodAppRS.repository.ShoppingListProductRepository;
import com.example.foodAppRS.service.ShoppingListProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShoppingListController {
    private final ShoppingListProductService shoppingListProductService;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final ShoppingListProductRepository shoppingListProductRepository;
    private final ShoppingListProductMapper shoppingListProductMapper;

    @Autowired
    public ShoppingListController(ShoppingListProductService shoppingListProductService,
                                  ProductRepository productRepository,
                                  AccountRepository accountRepository,
                                  ShoppingListProductRepository shoppingListProductRepository,
                                  ShoppingListProductMapper shoppingListProductMapper) {
        this.shoppingListProductService = shoppingListProductService;
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
        this.shoppingListProductRepository = shoppingListProductRepository;
        this.shoppingListProductMapper = shoppingListProductMapper;
    }

    @GetMapping("/shoppingList")
    public List<ShoppingListProductDTO> selectAllShoppingList() {
        return shoppingListProductService.selectAllShoppingList();
    }

    @GetMapping("shoppingList/account/{username}")
    public List<ShoppingListProductDTO> getFridgeByAccountUsername(@PathVariable(name="username") String username){
        List<ShoppingListProduct> shoppingList = shoppingListProductRepository.findShoppingListProductByAccount_Username(username);
        if (shoppingList.isEmpty()){
            //throw new ResourceNotFoundException("not found: " + username);
            return List.of();
        }
        return shoppingList.stream().map(shoppingListProductMapper).toList();
    }

    @PostMapping("/shoppingList")
    public ShoppingListProductDTO addProductToFridge(@RequestBody ShoppingListProductDTO shoppingDto){
        Product product = productRepository.findByNameIgnoreCase(shoppingDto.productName());
        if (product == null){
            product = new Product();
            product.setName(shoppingDto.productName());
            productRepository.save(product);
        }
        Optional<Account> account = accountRepository.getAccountByUsername(shoppingDto.username());
        if (account.isEmpty()){
            throw new ResourceNotFoundException("account not found: " + shoppingDto.username());
        }
        ShoppingListProduct shoppingProduct = new ShoppingListProduct();
        shoppingProduct.setAccount(account.get());
        shoppingProduct.setProduct(product);
        shoppingProduct.setQuantity(shoppingDto.quantity());
        shoppingListProductRepository.save(shoppingProduct);

        return new ShoppingListProductDTO(shoppingProduct.getId(),
                shoppingProduct.getAccount().getUsername(),
                shoppingProduct.getProduct().getName(),
                shoppingProduct.getQuantity());
    }

    @DeleteMapping("shoppingProduct/{id}")
    public void deleteFridgeItemByID(@PathVariable(name = "id") Integer id){
        shoppingListProductRepository.deleteById(id);
    }
}
