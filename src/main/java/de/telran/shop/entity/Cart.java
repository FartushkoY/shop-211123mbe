package de.telran.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private long cartID;

//    @OneToMany(mappedBy = "cart")
//    private List<CartItems> cartItems;
//
    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userID")
    private Users user;
}
