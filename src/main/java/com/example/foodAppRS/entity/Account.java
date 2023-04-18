package com.example.foodAppRS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="username", unique = true, nullable = false)
    private String userName;

    @JsonIgnore
    @Basic
    @Column(name="password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Fridge> fridgeList;
}
