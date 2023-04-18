package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Account;
import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FridgeController {
    private final FridgeRepository fridgeRepository;

    @Autowired
    public FridgeController(FridgeRepository inj){
        this.fridgeRepository = inj;
    }

    @GetMapping("fridge")
    public List<Fridge> getEveryFridge(){
        return fridgeRepository.findAll();
    }

    @GetMapping("fridge/account/{id}")
    public List<Fridge> getFridgeByAccountID(@PathVariable(name="id") Integer id){
        return fridgeRepository.findFridgeByUser_Id(id);
    }
}
