package com.example.foodAppRS.entity.dto;


import java.util.Date;

public record FridgeDTO (Integer id, String username, String productName, Date expirationDate){}
