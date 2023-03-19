package com.example.foodAppRS.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/")
    private String index(){
        return "Hello here foodRestApi - jdk17";
    }

    @GetMapping("/test")
    private String test(){
        return "Dawid Wolek - testing";
    }
}
