package com.example.foodAppRS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSeq")
    @SequenceGenerator(name="accSeq", initialValue = 150, allocationSize = 1)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="username", unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(name="password", nullable = false)
    private char[] password;


    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Fridge> fridgeList;
}
