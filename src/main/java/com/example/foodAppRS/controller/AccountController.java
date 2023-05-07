package com.example.foodAppRS.controller;

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
                             AccountRepository accountRepository){
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("account")
    public List<AccountDTO> getAllAccounts(){
        return accountService.selectAccounts();
    }

    @GetMapping("account/validate")
    public Boolean validateCredential(@RequestBody AccountDTO accountDTO){
        return accountRepository.existsAccountByUserName(accountDTO.username())
                && accountRepository.existsAccountByPassword(accountService.msgDigestSHA256(accountDTO.password()).toCharArray());
    }

    @PostMapping("account")
    public AccountDTO createNewAccount(@RequestBody AccountDTO accountDto) {
        return accountService.createNewAccount(accountDto);
    }

    @DeleteMapping("account/{id}")
    public void deleteAccountByID(@PathVariable(name = "id") Integer id){
        accountService.deleteAccountById(id);
    }
}
