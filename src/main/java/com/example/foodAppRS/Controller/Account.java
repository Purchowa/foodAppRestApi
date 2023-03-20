package com.example.foodAppRS.Controller;

import com.example.foodAppRS.Model.AccountModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Account {
    private static final Logger log = LoggerFactory.getLogger(Account.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/account/users")
    private List<AccountModel> users(){
        String sql = "SELECT id, username FROM account";
        List<AccountModel> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AccountModel.class));
        log.info("Succes: " + sql + " = " + users.size());
        return users;
    }
}
