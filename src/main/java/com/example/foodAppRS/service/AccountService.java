package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.entity.dto.AccountDTO;
import com.example.foodAppRS.entity.dto.AccountDTOMapper;
import com.example.foodAppRS.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService { // TODO moze bedzie jeszcze potrzeba pozostalych repository (fridge, product)
    private final AccountRepository accountRepository;
    private final AccountDTOMapper accountDTOMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountDTOMapper accountDTOMapper) {
        this.accountRepository = accountRepository;
        this.accountDTOMapper = accountDTOMapper;
    }

    public List<AccountDTO> selectAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountDTOMapper)
                .toList();
    }

    public AccountDTO createNewAccount(Account account) {

        Account toSave = new Account();
        toSave.setId(account.getId());
        toSave.setFirstName(account.getFirstName());
        toSave.setUserName(account.getUserName());
        toSave.setPassword(account.getPassword());
        toSave.setFridgeList(account.getFridgeList());

        accountRepository.save(toSave);

        return new AccountDTO(toSave.getFirstName(),
                toSave.getUserName());

    }

    public void deleteAccountById(Integer id) {
        Optional<Account> account = Optional.of(accountRepository.findById(id).get());
        accountRepository.delete(account.get());
    }

    public Optional<Account> selectAccountById(Integer id) {
        return Optional.of(accountRepository.findById(id)).get();
    }
}
