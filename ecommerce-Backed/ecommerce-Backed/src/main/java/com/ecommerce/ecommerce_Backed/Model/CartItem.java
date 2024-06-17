package com.ecommerce.ecommerce_Backed.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
    private String size;
    private int quantity;
    private Integer price;
    private Integer discountedPrice;
    private String userId;

    public CartItem() {
    }
}
