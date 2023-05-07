package com.example.foodAppRS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prodSeq")
    @SequenceGenerator(name = "prodSeq", initialValue = 150, allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "product") // mappedBy refers to property name!
    private List<Fridge> fridgeList;
}
