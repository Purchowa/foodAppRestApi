package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
