package com.ecommerce.ecommerce_Backed.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "oder_id")
    private String orderId;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems= new ArrayList<>();
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    @OneToOne
    private Address  shippingAddress;
    private Double totalPrice;
    private Integer totalDiscountedPrice;
    private Integer discount;
    private String orderStatus;
    private int totalItem;
    private LocalDateTime createdDate;


    public Order() {
    }
}
