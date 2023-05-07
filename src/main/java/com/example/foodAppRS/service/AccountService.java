package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.dto.AccountDTO;
import com.example.foodAppRS.entity.dto.AccountDTOMapper;
import com.example.foodAppRS.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class AccountService {
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

    public Boolean createNewAccount(AccountDTO accountDTO) {
        Account account = new Account();
        if(accountRepository.existsAccountByUsername(accountDTO.username())) {
            return false;
        }
        account.setFirstName(accountDTO.name());
        account.setUsername(accountDTO.username());
        account.setPassword(msgDigestSHA256(accountDTO.password()).toCharArray());

        accountRepository.save(account);
        return true;

    }

    public void deleteAccountById(Integer id) {
        Optional<Account> account = Optional.of(accountRepository.findById(id).get());
        accountRepository.delete(account.get());
    }

    public Boolean validateCredentials(AccountDTO accountDTO) {
        Boolean doesExistUsername = accountRepository.existsAccountByUsername(accountDTO.username());

        if (doesExistUsername) {
            Account repositoryAccount = accountRepository.findByUsername(accountDTO.username());
            char[] hashedRepoPassword = repositoryAccount.getPassword();
            char[] hashedDtoPassword = msgDigestSHA256(accountDTO.password()).toCharArray();
            return Arrays.equals(hashedRepoPassword, hashedDtoPassword);
        }

        return false;
    }

    public String msgDigestSHA256(char[] msg) {
        String hashMsg = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            byte[] hash = messageDigest.digest(charaTobytea(msg)); // if string here then String::getBytes
            hashMsg = HexFormat.of().formatHex(hash);
            //hashMsg = Base64.getEncoder().encodeToString(hash); // Plain text
            Arrays.fill(hash, (byte) 0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashMsg;
    }

    private byte[] charaTobytea(char[] input) {
        byte[] out = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            out[i] = (byte) input[i];
        }
        return out;
    }
}
