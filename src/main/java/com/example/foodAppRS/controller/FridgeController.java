package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.entity.Product;
import com.example.foodAppRS.entity.dto.FridgeDTO;
import com.example.foodAppRS.entity.dto.FridgeDTOMapper;
import com.example.foodAppRS.entity.dto.ProductDTO;
import com.example.foodAppRS.exception.type.ResourceNotFoundException;
import com.example.foodAppRS.repository.AccountRepository;
import com.example.foodAppRS.repository.FridgeRepository;
import com.example.foodAppRS.repository.ProductRepository;
import com.example.foodAppRS.service.AccountService;
import com.example.foodAppRS.service.FridgeService;
import com.example.foodAppRS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FridgeController {
    private final FridgeRepository fridgeRepository;
    private final FridgeDTOMapper fridgeDTOMapper;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;


    @Autowired
    public FridgeController(FridgeRepository fridgeRepository,
                            ProductRepository productRepository,
                            AccountRepository accountRepository,
                            FridgeDTOMapper fridgeDTOMapper){

        this.fridgeRepository = fridgeRepository;
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
        this.fridgeDTOMapper = fridgeDTOMapper;
    }

    // GET
    @GetMapping("fridge")
    public List<FridgeDTO> getAllFridges(){
        return fridgeRepository.findAll().stream().map(fridgeDTOMapper).toList();
    }

    @GetMapping("fridge/account/{id}") // TODO ja bym dał "account/{id}/fridge" - tu pobiera na podstawie id fridge a nie account
    public Optional<Fridge> getFridgeByAccountID(@PathVariable(name="id") Integer id){
        Optional<Fridge> fridge = fridgeRepository.findById(id);
        if (fridge.isEmpty()){
            throw new ResourceNotFoundException("not found: " + id);
        }
        return fridge;
    }

    // POST
    @PostMapping("fridge")
    public Product addProductToFridge(@RequestBody FridgeDTO fridgeDTO){
        Product product = productRepository.findByNameIgnoreCase(fridgeDTO.productName());
        if (product == null){
            product = new Product();
            product.setName(fridgeDTO.productName());
            productRepository.save(product);
        }

        return product;
        /*Optional<Account> account = accountRepository.getAccountByUserName(fridgeDTO.username());
        if (account.isEmpty()){
            throw new ResourceNotFoundException("account not found: " + fridgeDTO.username());
        }
        Fridge fridge = new Fridge();
        fridge.setAccount(account.get());
        fridge.setProduct(product);
        fridge.setExpirationDate(fridgeDTO.expirationDate());
        fridgeRepository.save(fridge);

        return new FridgeDTO(fridge.getAccount().getUserName(), fridge.getProduct().getName(), fridge.getExpirationDate());*/
    }
}
