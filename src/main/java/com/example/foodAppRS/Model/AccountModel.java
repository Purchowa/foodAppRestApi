package com.example.foodAppRS.Model;

public class AccountModel{
    private int id;
    private String username;
    public AccountModel(int id, String username){
        this.id = id;
        this.username = username;
    }
    public AccountModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
