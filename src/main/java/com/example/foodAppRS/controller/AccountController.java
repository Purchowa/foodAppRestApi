package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.dto.AccountDTO;
import com.example.foodAppRS.repository.AccountRepository;
import com.example.foodAppRS.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("accounts")
    public List<AccountDTO> getAllAccounts(){
        return accountService.selectAccounts();
    }

    @PostMapping("account")
    public AccountDTO createNewAccount(@RequestBody Account account) {
        return accountService.createNewAccount(account);
    }

    @DeleteMapping("account/{id}")
    public void deleteAccountByID(@PathVariable(name = "id") Integer id){
        accountService.deleteAccountById(id);
    }
}
