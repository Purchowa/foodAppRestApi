package com.example.foodAppRS.entity.dto;

import com.example.foodAppRS.entity.Account;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountDTOMapper implements Function<Account, AccountDTO> {
    @Override
    public AccountDTO apply(Account account) {
        return new AccountDTO(account.getFirstName(),
                account.getUserName(), account.getPassword());
    }
}
