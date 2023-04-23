package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.entity.Product;
import com.example.foodAppRS.entity.dto.FridgeDTO;
import com.example.foodAppRS.entity.dto.ProductDTO;
import com.example.foodAppRS.exception.type.ResourceNotFoundException;
import com.example.foodAppRS.service.AccountService;
import com.example.foodAppRS.service.FridgeService;
import com.example.foodAppRS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FridgeController {
//    private final FridgeRepository fridgeRepository;
//    private final ProductRepository productRepository;
//    private final AccountRepository accountRepository;
    private final FridgeService fridgeService;
    private final ProductService productService;
    private final AccountService accountService;

    @Autowired
    public FridgeController(FridgeService fridgeService,
                            ProductService productService,
                            AccountService accountService){
        this.fridgeService = fridgeService;
        this.productService = productService;
        this.accountService = accountService;
    }

    // GET
    @GetMapping("fridge")
    public List<FridgeDTO> getAllFridges(){
        return fridgeService.selectAllFridges();
    }

    @GetMapping("fridge/account/{id}") // TODO ja bym dał "account/{id}/fridge" - tu pobiera na podstawie id fridge a nie account
    public Optional<Fridge> getFridgeByAccountID(@PathVariable(name="id") Integer id){
        Optional<Fridge> fridge = fridgeService.selectFridgeById(id);
        if (fridge.isEmpty()){
            throw new ResourceNotFoundException("not found: " + id);
        }
        return fridge;
    }

    // POST
    @PostMapping("fridge")
    public Fridge addProductToFridge(@RequestBody Product product){ // Maybe I should do a service class?
        Optional<Fridge> fridgeOpt = fridgeService.selectFridgeById(product.getId());
        if (fridgeOpt.isEmpty()){
            product = new Product();
            product.setId(product.getId());
            product.setName(product.getName());
            ProductDTO productDTO = productService.addProductToFridge(product);
        }
        Optional<Account> account = accountService.selectAccountById(fridgeOpt.get().getId());
        if (account.isEmpty()){
            throw new ResourceNotFoundException("account not found: " + account.get().getId());
        }
        Fridge fridge = new Fridge();

        fridge.setAccount(account.get());
        fridge.setProduct(product);
        fridge.setExpirationDate(fridge.getExpirationDate());

        fridgeService.addToFridge(fridge);

        return fridge; // TODO: Better or different response
    }
}
