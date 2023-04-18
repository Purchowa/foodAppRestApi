package com.example.foodAppRS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    @Column(name="id")
    Integer id;

    @Column(name="first_name")
    String firstName;

    @Column(name="username")
    String userName;

    @JsonIgnore
    @Basic
    @Column(name="password")
    String password;
}
