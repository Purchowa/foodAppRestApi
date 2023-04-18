package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repo){
        this.repository = repo;
    }

    public List<Account> getAllAccounts(){
        return (List<Account>) repository.findAll();
    }
}
