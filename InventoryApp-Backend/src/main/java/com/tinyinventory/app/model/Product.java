package com.tinyinventory.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id //Primary-Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Unique, Auto-Increment
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_user")) // Creates FK constraint
    @OnDelete(action = OnDeleteAction.CASCADE) // Enables ON DELETE CASCADE
    private User user; // Foreign Key to User table

    //Alternative to foreign key, this
    /*@Column(nullable = false)
    private Long userId;*/

    @Column(nullable = false)
    private String name;

    @Column(nullable = false) // Ensures uniqueness for code
    private int code;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

}
