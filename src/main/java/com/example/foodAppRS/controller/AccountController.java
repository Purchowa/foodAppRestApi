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
    private final AccountRepository accountRepository;


    @Autowired
    public AccountController(AccountService accountService,
                             AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("accounts")
    public List<AccountDTO> getAllAccounts() {
        return accountService.selectAccounts();
    }

    @GetMapping("account/validate")
    public Boolean validateCredential(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "password") String password) {
        return accountService.validateCredentials(username, password);
    }

    @PostMapping("account")
    public Boolean createNewAccount(@RequestBody AccountDTO accountDto) {
        return accountService.createNewAccount(accountDto);
    }

    @DeleteMapping("account/{id}")
    public void deleteAccountByID(@PathVariable(name = "id") Integer id) {
        accountService.deleteAccountById(id);
    }
}
