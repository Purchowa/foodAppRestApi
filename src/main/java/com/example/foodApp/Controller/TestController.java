package com.example.foodApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String index(){
        return "Hello world!";
    }
    @GetMapping("/test")
    public String test(){
        return "Testing...";
    }

}
