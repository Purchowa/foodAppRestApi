package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.dto.AccountDTO;
import com.example.foodAppRS.entity.dto.AccountDTOMapper;
import com.example.foodAppRS.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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

    public AccountDTO createNewAccount(AccountDTO accountDTO) {
        String hashPassword = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            byte[] hash = messageDigest.digest(accountDTO.password().getBytes(StandardCharsets.UTF_8));
            hashPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Account account = new Account();
        account.setFirstName(accountDTO.name());
        account.setUserName(accountDTO.username());
        account.setPassword(hashPassword);

        System.out.println(account.getPassword());

        accountRepository.save(account);
        return new AccountDTO(account.getFirstName(),
                account.getUserName(), account.getPassword());

    }

    public void deleteAccountById(Integer id) {
        Optional<Account> account = Optional.of(accountRepository.findById(id).get());
        accountRepository.delete(account.get());
    }

}
