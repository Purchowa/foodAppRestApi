package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService serv){
        this.service = serv;
    }

    @GetMapping("/")
    public List<Account> getAllAccounts(){
        return service.getAllAccounts();
    }
}
