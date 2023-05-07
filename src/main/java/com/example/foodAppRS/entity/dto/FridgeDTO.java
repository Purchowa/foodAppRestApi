package com.example.foodAppRS.entity.dto;


import java.time.LocalDate;
import java.util.Date;

public record FridgeDTO (Integer id, String username, String productName, String expirationDate){}
