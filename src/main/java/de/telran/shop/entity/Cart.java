package de.telran.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private long cartID;

    @OneToMany(mappedBy = "cart")
    private List<CartItems> cartItems;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userID")
    private Users user;
}
