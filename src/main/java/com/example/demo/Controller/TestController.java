package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }
    @RequestMapping("/test")
    String test() {
        return "<h1Dane fajne, w końcu...</h1>";
    }
}
