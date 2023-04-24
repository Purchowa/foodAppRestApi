package com.example.foodAppRS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "fridge")
// Products in fridge
public class Fridge {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fridgeSeq")
    @SequenceGenerator(name = "fridgeSeq", initialValue = 150, allocationSize = 1)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
