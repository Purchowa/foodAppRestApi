package com.example.foodAppRS.controller;

import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.exception.types.FridgeNotFoundException;
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
        List<Fridge> fridgeList = fridgeRepository.findFridgeByUser_Id(id);
        if (fridgeList.isEmpty()){
            throw new FridgeNotFoundException("Not found: fridge/account/" + id);
        }
        return fridgeList;
    }
}
