package com.example.foodAppRS.repository;

import com.example.foodAppRS.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
