package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> getAccountByUsername(String username);
    public Boolean existsAccountByUsername(String username);

    Account findByUsername(String username);
}
