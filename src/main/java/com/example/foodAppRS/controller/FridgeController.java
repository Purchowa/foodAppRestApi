package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.entity.Product;
import com.example.foodAppRS.entity.dto.FridgeDTO;
import com.example.foodAppRS.exception.type.ResourceNotFoundException;
import com.example.foodAppRS.repository.AccountRepository;
import com.example.foodAppRS.repository.FridgeRepository;
import com.example.foodAppRS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FridgeController {
    private final FridgeRepository fridgeRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public FridgeController(FridgeRepository fRep, ProductRepository pRep, AccountRepository aRep){
        this.fridgeRepository = fRep;
        this.productRepository = pRep;
        this.accountRepository = aRep;
    }

    // GET
    @GetMapping("fridge")
    public List<Fridge> getEveryFridge(){
        return fridgeRepository.findAll();
    }

    @GetMapping("fridge/account/{id}")
    public List<Fridge> getFridgeByAccountID(@PathVariable(name="id") Integer id){
        List<Fridge> fridgeList = fridgeRepository.findFridgeByAccount_Id(id);
        if (fridgeList.isEmpty()){
            throw new ResourceNotFoundException("not found: " + id);
        }
        return fridgeList;
    }

    // POST
    @PostMapping("fridge")
    public Fridge addProductToFridge(@RequestBody FridgeDTO fridgeDTO){ // Maybe I should do a service class?
        Product product = productRepository.findByNameIgnoreCase(fridgeDTO.productName());
        if (product == null){
            product = new Product();
            product.setName(fridgeDTO.productName());
            productRepository.save(product);
        }
        Optional<Account> account = accountRepository.findById(fridgeDTO.accountID());
        if (account.isEmpty()){
            throw new ResourceNotFoundException("account not found: " + fridgeDTO.accountID());
        }
        Fridge fridge = new Fridge();
        fridge.setAccount(account.get());
        fridge.setProduct(product);
        fridge.setExpirationDate(fridgeDTO.expirationDate());
        fridgeRepository.save(fridge);

        return fridge; // TODO: Better or different response
    }
}
