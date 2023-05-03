package com.example.foodAppRS.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_list")
public class ShoppingListProduct {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shoppingSeq")
    @SequenceGenerator(name = "shoppingSeq", initialValue = 150, allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
