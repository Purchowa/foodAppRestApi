package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.dto.AccountDTO;
import com.example.foodAppRS.entity.dto.AccountDTOMapper;
import com.example.foodAppRS.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
        Account account = new Account();
        account.setFirstName(accountDTO.name());
        account.setUserName(accountDTO.username());
        account.setPassword(msgDigestSHA256(accountDTO.password()).toCharArray());

        accountRepository.save(account);
        return new AccountDTO(account.getFirstName(),
                account.getUserName(), account.getPassword());

    }

    public void deleteAccountById(Integer id) {
        Optional<Account> account = Optional.of(accountRepository.findById(id).get());
        accountRepository.delete(account.get());
    }

    public String msgDigestSHA256(char[] msg){
        String hashMsg = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            byte[] hash = messageDigest.digest(charaTobytea(msg)); // if string here then String::getBytes
            hashMsg = HexFormat.of().formatHex(hash);
            // hashMsg = Base64.getEncoder().encodeToString(hash); // Plain text
            Arrays.fill(hash, (byte)0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashMsg;
    }

    private byte[] charaTobytea(char[] input){
        byte[] out = new byte[input.length];
        for (int i = 0; i < input.length; i++){
            out[i] = (byte)input[i];
        }
        return out;
    }
}
