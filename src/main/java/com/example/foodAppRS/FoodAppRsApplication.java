package com.example.foodAppRS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FoodAppRsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodAppRsApplication.class, args);
	}

	@GetMapping("/")
	public String helloWorld(){
		return "Hello here foodRestApi - jdk17";
	}

}
