package com.example.foodAppRS.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_list")
public class ShoppingListProduct {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shoppingSeq")
    @SequenceGenerator(name = "shoppingSeq", initialValue = 150, allocationSize = 1)
    @Column(name = "id", unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account userId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "quantity")
    private Integer quantity;

}
