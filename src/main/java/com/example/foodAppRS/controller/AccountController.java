package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository inj){
        this.accountRepository = inj;
    }

    @GetMapping("account")
    public List<Account> getAllAccounts(){
        return (List<Account>)accountRepository.findAll();
    }

    @DeleteMapping("account/{id}")
    public void deleteAccountByID(@PathVariable(name = "id") Integer id){
        accountRepository.deleteById(id);
    }
}
